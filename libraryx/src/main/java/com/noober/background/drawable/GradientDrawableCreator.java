package com.noober.background.drawable;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;

import com.noober.background.R;

import org.xmlpull.v1.XmlPullParserException;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static android.graphics.drawable.GradientDrawable.LINEAR_GRADIENT;

public class GradientDrawableCreator implements ICreateDrawable {

    private TypedArray typedArray;

    GradientDrawableCreator(TypedArray typedArray) {
        this.typedArray = typedArray;
    }

    @Override
    public Drawable create() throws XmlPullParserException {
        GradientDrawable drawable = new GradientDrawable();
        float[] cornerRadius = new float[8];
        float sizeWidth = 0;
        float sizeHeight = 0;
        float strokeWidth = -1;
        float strokeDashWidth = 0;
        int strokeColor = 0;
        int solidColor = 0;
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
            int attr = typedArray.getIndex(i);
            if (attr == R.styleable.background_bl_shape) {
                drawable.setShape(typedArray.getInt(attr, 0));
            } else if (attr == R.styleable.background_bl_solid_color) {
                solidColor = typedArray.getColor(attr, 0);
            } else if (attr == R.styleable.background_bl_corners_radius) {
                drawable.setCornerRadius(typedArray.getDimension(attr, 0));
            } else if (attr == R.styleable.background_bl_corners_bottomLeftRadius) {
                cornerRadius[6] = typedArray.getDimension(attr, 0);
                cornerRadius[7] = typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.background_bl_corners_bottomRightRadius) {
                cornerRadius[4] = typedArray.getDimension(attr, 0);
                cornerRadius[5] = typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.background_bl_corners_topLeftRadius) {
                cornerRadius[0] = typedArray.getDimension(attr, 0);
                cornerRadius[1] = typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.background_bl_corners_topRightRadius) {
                cornerRadius[2] = typedArray.getDimension(attr, 0);
                cornerRadius[3] = typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.background_bl_gradient_angle) {
                gradientAngle = typedArray.getInteger(attr, 0);
            } else if (attr == R.styleable.background_bl_gradient_centerX) {
                centerX = typedArray.getFloat(attr, -1);
            } else if (attr == R.styleable.background_bl_gradient_centerY) {
                centerY = typedArray.getFloat(attr, -1);
            } else if (attr == R.styleable.background_bl_gradient_centerColor) {
                centerColor = typedArray.getColor(attr, 0);
            } else if (attr == R.styleable.background_bl_gradient_endColor) {
                endColor = typedArray.getColor(attr, 0);
            } else if (attr == R.styleable.background_bl_gradient_startColor) {
                startColor = typedArray.getColor(attr, 0);
            } else if (attr == R.styleable.background_bl_gradient_gradientRadius) {
                drawable.setGradientRadius(typedArray.getDimension(attr, 0));
            } else if (attr == R.styleable.background_bl_gradient_type) {
                gradientType = typedArray.getInt(attr, 0);
                drawable.setGradientType(gradientType);
            } else if (attr == R.styleable.background_bl_gradient_useLevel) {
                drawable.setUseLevel(typedArray.getBoolean(attr, false));
            } else if (attr == R.styleable.background_bl_padding_left) {
                padding.left = (int) typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.background_bl_padding_top) {
                padding.top = (int) typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.background_bl_padding_right) {
                padding.right = (int) typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.background_bl_padding_bottom) {
                padding.bottom = (int) typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.background_bl_size_width) {
                sizeWidth = typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.background_bl_size_height) {
                sizeHeight = typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.background_bl_stroke_width) {
                strokeWidth = typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.background_bl_stroke_color) {
                strokeColor = typedArray.getColor(attr, 0);
            } else if (attr == R.styleable.background_bl_stroke_dashWidth) {
                strokeDashWidth = typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.background_bl_stroke_dashGap) {
                strokeGap = typedArray.getDimension(attr, 0);
            }
        }
        if (hasSetRadius(cornerRadius)) {
            drawable.setCornerRadii(cornerRadius);
        }
        if (typedArray.hasValue(R.styleable.background_bl_size_width) &&
                typedArray.hasValue(R.styleable.background_bl_size_height)) {
            drawable.setSize((int) sizeWidth, (int) sizeHeight);
        }
        //设置填充颜色
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int start = 0;
            ArrayList<Integer> stateList = new ArrayList<>();
            ArrayList<Integer> colorList = new ArrayList<>();
            if (typedArray.hasValue(R.styleable.background_bl_pressed_solid_color) &&
                    typedArray.hasValue(R.styleable.background_bl_unPressed_solid_color)) {
                stateList.add(android.R.attr.state_pressed);
                stateList.add(-android.R.attr.state_pressed);
                colorList.add(typedArray.getColor(R.styleable.background_bl_pressed_solid_color, 0));
                colorList.add(typedArray.getColor(R.styleable.background_bl_unPressed_solid_color, 0));
            }
            if (typedArray.hasValue(R.styleable.background_bl_checkable_solid_color) &&
                    typedArray.hasValue(R.styleable.background_bl_unCheckable_solid_color)) {
                stateList.add(android.R.attr.state_checkable);
                stateList.add(-android.R.attr.state_checkable);
                colorList.add(typedArray.getColor(R.styleable.background_bl_checkable_solid_color, 0));
                colorList.add(typedArray.getColor(R.styleable.background_bl_unCheckable_solid_color, 0));
            }
            if (typedArray.hasValue(R.styleable.background_bl_checked_solid_color) &&
                    typedArray.hasValue(R.styleable.background_bl_unChecked_solid_color)) {
                stateList.add(android.R.attr.state_checked);
                stateList.add(-android.R.attr.state_checked);
                colorList.add(typedArray.getColor(R.styleable.background_bl_checked_solid_color, 0));
                colorList.add(typedArray.getColor(R.styleable.background_bl_unChecked_solid_color, 0));
            }
            if (typedArray.hasValue(R.styleable.background_bl_enabled_solid_color) &&
                    typedArray.hasValue(R.styleable.background_bl_unEnabled_solid_color)) {
                stateList.add(android.R.attr.state_enabled);
                stateList.add(-android.R.attr.state_enabled);
                colorList.add(typedArray.getColor(R.styleable.background_bl_enabled_solid_color, 0));
                colorList.add(typedArray.getColor(R.styleable.background_bl_unEnabled_solid_color, 0));
            }
            if (typedArray.hasValue(R.styleable.background_bl_selected_solid_color) &&
                    typedArray.hasValue(R.styleable.background_bl_unSelected_solid_color)) {
                stateList.add(android.R.attr.state_selected);
                stateList.add(-android.R.attr.state_selected);
                colorList.add(typedArray.getColor(R.styleable.background_bl_selected_solid_color, 0));
                colorList.add(typedArray.getColor(R.styleable.background_bl_unSelected_solid_color, 0));
            }
            if (typedArray.hasValue(R.styleable.background_bl_focused_solid_color) &&
                    typedArray.hasValue(R.styleable.background_bl_unFocused_solid_color)) {
                stateList.add(android.R.attr.state_focused);
                stateList.add(-android.R.attr.state_focused);
                colorList.add(typedArray.getColor(R.styleable.background_bl_focused_solid_color, 0));
                colorList.add(typedArray.getColor(R.styleable.background_bl_unFocused_solid_color, 0));
            }

            if (stateList.size() > 0) {
                int[][] state = new int[stateList.size()][];
                int[] color = new int[stateList.size()];
                for (int iState : stateList) {
                    state[start] = new int[]{iState};
                    color[start] = colorList.get(start);
                    start++;
                }
                ColorStateList colorStateList = new ColorStateList(state, color);
                drawable.setColor(colorStateList);
            } else if (typedArray.hasValue(R.styleable.background_bl_solid_color)) {
                drawable.setColor(solidColor);
            }
            stateList = null;
            colorList = null;
        } else if (typedArray.hasValue(R.styleable.background_bl_solid_color)) {
            drawable.setColor(solidColor);
        }

        //设置边框颜色
        if (typedArray.hasValue(R.styleable.background_bl_stroke_width)) {
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                int start = 0;
                ArrayList<Integer> stateList = new ArrayList<>();
                ArrayList<Integer> colorList = new ArrayList<>();
                if (typedArray.hasValue(R.styleable.background_bl_pressed_stroke_color) &&
                        typedArray.hasValue(R.styleable.background_bl_unPressed_stroke_color)) {
                    stateList.add(android.R.attr.state_pressed);
                    stateList.add(-android.R.attr.state_pressed);
                    colorList.add(typedArray.getColor(R.styleable.background_bl_pressed_stroke_color, 0));
                    colorList.add(typedArray.getColor(R.styleable.background_bl_unPressed_stroke_color, 0));
                }
                if (typedArray.hasValue(R.styleable.background_bl_checkable_stroke_color) &&
                        typedArray.hasValue(R.styleable.background_bl_unCheckable_stroke_color)) {
                    stateList.add(android.R.attr.state_checkable);
                    stateList.add(-android.R.attr.state_checkable);
                    colorList.add(typedArray.getColor(R.styleable.background_bl_checkable_stroke_color, 0));
                    colorList.add(typedArray.getColor(R.styleable.background_bl_unCheckable_stroke_color, 0));
                }
                if (typedArray.hasValue(R.styleable.background_bl_checked_stroke_color) &&
                        typedArray.hasValue(R.styleable.background_bl_unChecked_stroke_color)) {
                    stateList.add(android.R.attr.state_checked);
                    stateList.add(-android.R.attr.state_checked);
                    colorList.add(typedArray.getColor(R.styleable.background_bl_checked_stroke_color, 0));
                    colorList.add(typedArray.getColor(R.styleable.background_bl_unChecked_stroke_color, 0));
                }
                if (typedArray.hasValue(R.styleable.background_bl_enabled_stroke_color) &&
                        typedArray.hasValue(R.styleable.background_bl_unEnabled_stroke_color)) {
                    stateList.add(android.R.attr.state_enabled);
                    stateList.add(-android.R.attr.state_enabled);
                    colorList.add(typedArray.getColor(R.styleable.background_bl_enabled_stroke_color, 0));
                    colorList.add(typedArray.getColor(R.styleable.background_bl_unEnabled_stroke_color, 0));
                }
                if (typedArray.hasValue(R.styleable.background_bl_selected_stroke_color) &&
                        typedArray.hasValue(R.styleable.background_bl_unSelected_stroke_color)) {
                    stateList.add(android.R.attr.state_selected);
                    stateList.add(-android.R.attr.state_selected);
                    colorList.add(typedArray.getColor(R.styleable.background_bl_selected_stroke_color, 0));
                    colorList.add(typedArray.getColor(R.styleable.background_bl_unSelected_stroke_color, 0));
                }
                if (typedArray.hasValue(R.styleable.background_bl_focused_stroke_color) &&
                        typedArray.hasValue(R.styleable.background_bl_unFocused_stroke_color)) {
                    stateList.add(android.R.attr.state_focused);
                    stateList.add(-android.R.attr.state_focused);
                    colorList.add(typedArray.getColor(R.styleable.background_bl_focused_stroke_color, 0));
                    colorList.add(typedArray.getColor(R.styleable.background_bl_unFocused_stroke_color, 0));
                }
                if (stateList.size() > 0) {
                    int[][] state = new int[stateList.size()][];
                    int[] color = new int[stateList.size()];
                    for (int iState : stateList) {
                        state[start] = new int[]{iState};
                        color[start] = colorList.get(start);
                        start++;
                    }
                    ColorStateList colorStateList = new ColorStateList(state, color);
                    drawable.setStroke((int) strokeWidth, colorStateList, strokeDashWidth, strokeGap);
                } else if (typedArray.hasValue(R.styleable.background_bl_stroke_color)) {
                    drawable.setStroke((int) strokeWidth, strokeColor, strokeDashWidth, strokeGap);
                }

                stateList = null;
                colorList = null;
            } else if (typedArray.hasValue(R.styleable.background_bl_stroke_color)) {
                drawable.setStroke((int) strokeWidth, strokeColor, strokeDashWidth, strokeGap);
            }
        }

        if (typedArray.hasValue(R.styleable.background_bl_gradient_centerX) &&
                typedArray.hasValue(R.styleable.background_bl_gradient_centerY)) {
            drawable.setGradientCenter(centerX, centerY);
        }

        if (typedArray.hasValue(R.styleable.background_bl_gradient_startColor) &&
                typedArray.hasValue(R.styleable.background_bl_gradient_endColor) &&
                android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            int[] colors;
            if (typedArray.hasValue(R.styleable.background_bl_gradient_centerColor)) {
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
                typedArray.hasValue(R.styleable.background_bl_gradient_angle) &&
                android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
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

        if (typedArray.hasValue(R.styleable.background_bl_padding_left) &&
                typedArray.hasValue(R.styleable.background_bl_padding_top) &&
                typedArray.hasValue(R.styleable.background_bl_padding_right) &&
                typedArray.hasValue(R.styleable.background_bl_padding_bottom)) {
            try {
                Field paddingField = drawable.getClass().getField("mPadding");
                paddingField.setAccessible(true);
                paddingField.set(drawable, padding);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        return drawable;
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
}
