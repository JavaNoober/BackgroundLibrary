package com.noober.background;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.noober.background.utils.TypeValueHelper;

import org.xmlpull.v1.XmlPullParserException;

import java.lang.reflect.Field;

import static android.graphics.drawable.GradientDrawable.LINEAR_GRADIENT;

public class BackgroundFactory implements LayoutInflater.Factory {

    private LayoutInflater.Factory mViewCreateFactory;

    public void setInterceptFactory(LayoutInflater.Factory factory) {
        mViewCreateFactory = factory;
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = null;
        if (mViewCreateFactory != null) {
            view = mViewCreateFactory.onCreateView(name, context, attrs);
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.background);
        TypedArray pressTa = context.obtainStyledAttributes(attrs, R.styleable.background_press);
        TypedArray selectorTa = context.obtainStyledAttributes(attrs, R.styleable.background_selector);

        try {
            int attrCount = typedArray.getIndexCount();
            if (attrCount == 0) {
                return null;
            }
            if (view == null) {
                view = createView(context, name, attrs);
            }
            if (view == null) {
                return null;
            }


            GradientDrawable drawable = getDrawable(typedArray);

            if(selectorTa.getIndexCount() > 0){
                StateListDrawable stateListDrawable = getSelectorDrawable(typedArray, selectorTa);
//                view.setClickable(true);
//                view.setBackground(stateListDrawable);
            }

            // todo 适配5.0以下
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
                    typedArray.hasValue(R.styleable.background_ripple_enable)) {
                if(typedArray.getBoolean(R.styleable.background_ripple_enable, false)){
                    int color = typedArray.getColor(R.styleable.background_ripple_color, 0);
                    RippleDrawable rippleDrawable = new RippleDrawable(ColorStateList.valueOf(color), drawable, getShape());
                    view.setClickable(true);
                    view.setBackground(rippleDrawable);
                    return view;
                }

            }

            if(pressTa.getIndexCount() > 0){
                StateListDrawable stateListDrawable = getStateListDrawable(drawable, getDrawable(typedArray), pressTa);
                view.setClickable(true);
                view.setBackground(stateListDrawable);
            }else {
                view.setBackground(drawable);
            }
            return view;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            typedArray.recycle();
            pressTa.recycle();
            selectorTa.recycle();
        }

        return view;
    }

    private View createView(Context context, String name, AttributeSet attrs) {
        View view = null;
        try {
            if (-1 == name.indexOf('.')) {
                if ("View".equals(name)) {
                    view = LayoutInflater.from(context).createView(name, "android.view.", attrs);
                }
                if (view == null) {
                    view = LayoutInflater.from(context).createView(name, "android.widget.", attrs);
                }
                if (view == null) {
                    view = LayoutInflater.from(context).createView(name, "android.webkit.", attrs);
                }
            } else {
                view = LayoutInflater.from(context).createView(name, null, attrs);
            }

        } catch (Exception e) {
            view = null;
        }
        return view;
    }

    private boolean hasSetRadius(float[] radius) {
        boolean hasSet = false;
        for (float f : radius) {
            if (f != 0.0f) {
                hasSet = true;
                break;
            }
        }
        return hasSet;
    }

    private GradientDrawable getDrawable(TypedArray typedArray) throws Exception {
        GradientDrawable drawable = new GradientDrawable();
        float[] cornerRadius = new float[8];
        float sizeWidth = 0;
        float sizeHeight = 0;
        float strokeWidth = -1;
        float strokeDashWidth = 0;
        int strokeColor = 1;
        float strokeGap = 0;
        float centerX = 0;
        float centerY = 0;
        int centerColor = 0;
        int startColor = 0;
        int endColor = 0;
        int gradientType = LINEAR_GRADIENT;
        int gradientAngle = 0;
        Rect padding = new Rect();
        for (int i = 0; i < typedArray.getIndexCount(); i++) {
            int attr = TypeValueHelper.sAppearanceValues.get(typedArray.getIndex(i), -1);
            if (attr == -1) {
                continue;
            }
            int typeIndex = typedArray.getIndex(i);
            if (attr == R.styleable.background_shape) {
                drawable.setShape(typedArray.getInt(typeIndex, 0));
            } else if (attr == R.styleable.background_solid_color) {
                drawable.setColor(typedArray.getColor(typeIndex, 0));
            } else if (attr == R.styleable.background_corners_radius) {
                drawable.setCornerRadius(typedArray.getDimension(typeIndex, 0));
            } else if (attr == R.styleable.background_corners_bottomLeftRadius) {
                cornerRadius[6] = typedArray.getDimension(typeIndex, 0);
                cornerRadius[7] = typedArray.getDimension(typeIndex, 0);
            } else if (attr == R.styleable.background_corners_bottomRightRadius) {
                cornerRadius[4] = typedArray.getDimension(typeIndex, 0);
                cornerRadius[5] = typedArray.getDimension(typeIndex, 0);
            } else if (attr == R.styleable.background_corners_topLeftRadius) {
                cornerRadius[0] = typedArray.getDimension(typeIndex, 0);
                cornerRadius[1] = typedArray.getDimension(typeIndex, 0);
            } else if (attr == R.styleable.background_corners_topRightRadius) {
                cornerRadius[2] = typedArray.getDimension(typeIndex, 0);
                cornerRadius[3] = typedArray.getDimension(typeIndex, 0);
            } else if (attr == R.styleable.background_gradient_angle) {
                gradientAngle = typedArray.getInteger(typeIndex, 0);
            } else if (attr == R.styleable.background_gradient_centerX) {
                centerX = typedArray.getFloat(typeIndex, -1);
            } else if (attr == R.styleable.background_gradient_centerY) {
                centerY = typedArray.getFloat(typeIndex, -1);
            } else if (attr == R.styleable.background_gradient_centerColor) {
                centerColor = typedArray.getColor(typeIndex, 0);
            } else if (attr == R.styleable.background_gradient_endColor) {
                endColor = typedArray.getColor(typeIndex, 0);
            } else if (attr == R.styleable.background_gradient_startColor) {
                startColor = typedArray.getColor(typeIndex, 0);
            } else if (attr == R.styleable.background_gradient_gradientRadius) {
                drawable.setGradientRadius(typedArray.getDimension(typeIndex, 0));
            } else if (attr == R.styleable.background_gradient_type) {
                gradientType = typedArray.getInt(typeIndex, 0);
                drawable.setGradientType(gradientType);
            } else if (attr == R.styleable.background_gradient_useLevel) {
                drawable.setUseLevel(typedArray.getBoolean(typeIndex, false));
            } else if (attr == R.styleable.background_padding_left) {
                padding.left = (int) typedArray.getDimension(typeIndex, 0);
            } else if (attr == R.styleable.background_padding_top) {
                padding.top = (int) typedArray.getDimension(typeIndex, 0);
            } else if (attr == R.styleable.background_padding_right) {
                padding.right = (int) typedArray.getDimension(typeIndex, 0);
            } else if (attr == R.styleable.background_padding_bottom) {
                padding.bottom = (int) typedArray.getDimension(typeIndex, 0);
            } else if (attr == R.styleable.background_size_width) {
                sizeWidth = typedArray.getDimension(typeIndex, 0);
            } else if (attr == R.styleable.background_size_height) {
                sizeHeight = typedArray.getDimension(typeIndex, 0);
            } else if (attr == R.styleable.background_stroke_width) {
                strokeWidth = typedArray.getDimension(typeIndex, 0);
            } else if (attr == R.styleable.background_stroke_color) {
                strokeColor = typedArray.getColor(typeIndex, 0);
            } else if (attr == R.styleable.background_stroke_dashWidth) {
                strokeDashWidth = typedArray.getDimension(typeIndex, 0);
            } else if (attr == R.styleable.background_stroke_dashGap) {
                strokeGap = typedArray.getDimension(typeIndex, 0);
            }
        }
        if (hasSetRadius(cornerRadius)) {
            drawable.setCornerRadii(cornerRadius);
        }
        if (typedArray.hasValue(R.styleable.background_size_width) &&
                typedArray.hasValue(R.styleable.background_size_height)) {
            drawable.setSize((int) sizeWidth, (int) sizeHeight);
        }
        if (typedArray.hasValue(R.styleable.background_stroke_width) &&
                typedArray.hasValue(R.styleable.background_stroke_color)) {
            drawable.setStroke((int) strokeWidth, strokeColor, strokeDashWidth, strokeGap);
        }
        if (typedArray.hasValue(R.styleable.background_gradient_centerX) &&
                typedArray.hasValue(R.styleable.background_gradient_centerY)) {
            drawable.setGradientCenter(centerX, centerY);
        }

        if (typedArray.hasValue(R.styleable.background_gradient_startColor) &&
                typedArray.hasValue(R.styleable.background_gradient_endColor)) {
            int[] colors;
            if (typedArray.hasValue(R.styleable.background_gradient_centerColor)) {
                colors = new int[3];
                colors[0] = startColor;
                colors[1] = centerColor;
                colors[2] = endColor;
            } else {
                colors = new int[2];
                colors[0] = startColor;
                colors[1] = endColor;
            }
            drawable.setColors(colors);
        }
        if (gradientType == LINEAR_GRADIENT &&
                typedArray.hasValue(R.styleable.background_gradient_angle)) {
            gradientAngle %= 360;
            if (gradientAngle % 45 != 0) {
                throw new XmlPullParserException(typedArray.getPositionDescription()
                        + "<gradient> tag requires 'angle' attribute to "
                        + "be a multiple of 45");
            }
            GradientDrawable.Orientation mOrientation = GradientDrawable.Orientation.LEFT_RIGHT;
            switch (gradientAngle) {
                case 0:
                    mOrientation = GradientDrawable.Orientation.LEFT_RIGHT;
                    break;
                case 45:
                    mOrientation = GradientDrawable.Orientation.BL_TR;
                    break;
                case 90:
                    mOrientation = GradientDrawable.Orientation.BOTTOM_TOP;
                    break;
                case 135:
                    mOrientation = GradientDrawable.Orientation.BR_TL;
                    break;
                case 180:
                    mOrientation = GradientDrawable.Orientation.RIGHT_LEFT;
                    break;
                case 225:
                    mOrientation = GradientDrawable.Orientation.TR_BL;
                    break;
                case 270:
                    mOrientation = GradientDrawable.Orientation.TOP_BOTTOM;
                    break;
                case 315:
                    mOrientation = GradientDrawable.Orientation.TL_BR;
                    break;
            }
            drawable.setOrientation(mOrientation);
        }

        if (typedArray.hasValue(R.styleable.background_padding_left) &&
                typedArray.hasValue(R.styleable.background_padding_top) &&
                typedArray.hasValue(R.styleable.background_padding_right) &&
                typedArray.hasValue(R.styleable.background_padding_bottom)) {
            Field paddingField = drawable.getClass().getField("mPadding");
            paddingField.setAccessible(true);
            paddingField.set(drawable, padding);
        }
        return drawable;
    }

    private StateListDrawable getStateListDrawable(GradientDrawable drawable, GradientDrawable pressDrawable, TypedArray typedArray){
        StateListDrawable stateListDrawable = new StateListDrawable();
        for (int i = 0; i < typedArray.getIndexCount(); i++){
            int attr = TypeValueHelper.sAppearancePressValues.get(typedArray.getIndex(i), -1);
            if(attr == -1){
                continue;
            }
            int typeIndex = typedArray.getIndex(i);

            if(attr == R.styleable.background_press_pressed_color){
                int color = typedArray.getColor(typeIndex, 0);
                pressDrawable.setColor(color);
                stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, pressDrawable);
            }else if(attr == R.styleable.background_press_unpressed_color){
                int color = typedArray.getColor(typeIndex, 0);
                drawable.setColor(color);
                stateListDrawable.addState(new int[]{-android.R.attr.state_pressed}, drawable);
            }
        }
        return stateListDrawable;
    }

    private StateListDrawable getSelectorDrawable(TypedArray typedArray, TypedArray selectorTa) throws Exception {
        StateListDrawable stateListDrawable = new StateListDrawable();

        for (int i = 0; i < selectorTa.getIndexCount(); i++){
            int attr = TypeValueHelper.sAppearanceSelectorValues.get(selectorTa.getIndex(i), -1);
            if(attr == -1){
                continue;
            }
            int typeIndex = selectorTa.getIndex(i);

            if(attr == R.styleable.background_selector_checkable_drawable){
                setSelectorDrawable(typedArray, selectorTa, stateListDrawable, typeIndex, android.R.attr.state_pressed);
            }else if(attr == R.styleable.background_selector_unCheckable_drawable){
                setSelectorDrawable(typedArray, selectorTa, stateListDrawable, typeIndex, -android.R.attr.state_pressed);
            }
        }
        return stateListDrawable;
    }

    private void setSelectorDrawable(TypedArray typedArray, TypedArray selectorTa,
                                     StateListDrawable stateListDrawable, int typeIndex, @AttrRes int functionId) throws Exception {
        int color = 0;
        Drawable resDrawable = null;

        //这里用try catch先判断是否是颜色而不是直接调用getDrawable，为了方便填入的是颜色时可以沿用其他属性,
        //否则如果是其他资源会覆盖app:corners_radius等其他shape属性设置的效果
        try {
            color = selectorTa.getColor(typeIndex, 0);
            if(color == 0){
                resDrawable = selectorTa.getDrawable(typeIndex);
            }
        }catch (Exception e){
            resDrawable = selectorTa.getDrawable(typeIndex);
        }
        if(resDrawable == null && color != 0){
            GradientDrawable tmpDrawable = getDrawable(typedArray);
            tmpDrawable.setColor(color);
            stateListDrawable.addState(new int[]{functionId}, tmpDrawable);
        }else {
            stateListDrawable.addState(new int[]{functionId}, resDrawable);
        }
    }

    private Drawable getShape(){
        return new ShapeDrawable(new RectShape(){
            @Override
            public void draw(Canvas canvas, Paint paint) {
                final float width = this.getWidth();
                final float height = this.getHeight();
                Path path = new Path();
                path.moveTo(0,0);
                path.lineTo(width,0);
                path.lineTo(width,height);
                path.lineTo(0,height);
                path.lineTo(0,0);
                path.close();
                canvas.drawPath(path,paint);
            }
        });
    }

}
