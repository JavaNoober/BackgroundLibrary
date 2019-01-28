package com.noober.background;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.v4.util.ArrayMap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.noober.background.drawable.DrawableFactory;

import java.lang.reflect.Constructor;
import java.util.Map;

public class BackgroundFactory implements LayoutInflater.Factory2 {

    private LayoutInflater.Factory mViewCreateFactory;
    private LayoutInflater.Factory2 mViewCreateFactory2;

    private static final Class<?>[] sConstructorSignature = new Class[]{Context.class, AttributeSet.class};
    private final Object[] mConstructorArgs = new Object[2];
    private static final Map<String, Constructor<? extends View>> sConstructorMap = new ArrayMap<>();

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = null;
        if (mViewCreateFactory2 != null) {
            view = mViewCreateFactory2.onCreateView(name, context, attrs);
            if (view == null) {
                view = mViewCreateFactory2.onCreateView(null, name, context, attrs);
            }
        } else if (mViewCreateFactory != null) {
            view = mViewCreateFactory.onCreateView(name, context, attrs);
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.background);
        TypedArray pressTa = context.obtainStyledAttributes(attrs, R.styleable.background_press);
        TypedArray selectorTa = context.obtainStyledAttributes(attrs, R.styleable.background_selector);
        TypedArray textTa = context.obtainStyledAttributes(attrs, R.styleable.text_selector);
        TypedArray buttonTa = context.obtainStyledAttributes(attrs, R.styleable.background_button_drawable);
        TypedArray otherTa = context.obtainStyledAttributes(attrs, R.styleable.bl_other);
        try {
            if (typedArray.getIndexCount() == 0 && selectorTa.getIndexCount() == 0
                    && pressTa.getIndexCount() == 0 && textTa.getIndexCount() == 0 && buttonTa.getIndexCount() == 0) {
                return view;
            }
            if (view == null) {
                view = createViewFromTag(context, name, attrs);
            }
            if (view == null) {
                return null;
            }

            GradientDrawable drawable = null;
            StateListDrawable stateListDrawable = null;
            if(buttonTa.getIndexCount() > 0 && view instanceof CompoundButton){
                view.setClickable(true);
                ((CompoundButton) view).setButtonDrawable(DrawableFactory.getButtonDrawable(typedArray, buttonTa));
            } else if (selectorTa.getIndexCount() > 0) {
                stateListDrawable = DrawableFactory.getSelectorDrawable(typedArray, selectorTa);
                view.setClickable(true);
                setDrawable(stateListDrawable, view, otherTa);
            } else if (pressTa.getIndexCount() > 0) {
                drawable = DrawableFactory.getDrawable(typedArray);
                stateListDrawable = DrawableFactory.getPressDrawable(drawable, typedArray, pressTa);
                view.setClickable(true);
                setDrawable(stateListDrawable, view, otherTa);
            } else if(typedArray.getIndexCount() > 0){
                drawable = DrawableFactory.getDrawable(typedArray);
                setDrawable(drawable, view, otherTa);
            }

            if (view instanceof TextView && textTa.getIndexCount() > 0) {
                ((TextView) view).setTextColor(DrawableFactory.getTextSelectorColor(textTa));
            }

            if (typedArray.getBoolean(R.styleable.background_bl_ripple_enable, false) &&
                    typedArray.hasValue(R.styleable.background_bl_ripple_color)) {
                int color = typedArray.getColor(R.styleable.background_bl_ripple_color, 0);
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Drawable contentDrawable = (stateListDrawable == null ? drawable : stateListDrawable);
                    RippleDrawable rippleDrawable = new RippleDrawable(ColorStateList.valueOf(color), contentDrawable, contentDrawable);
                    view.setClickable(true);
                    view.setBackground(rippleDrawable);
                } else if(stateListDrawable == null){
                    StateListDrawable tmpDrawable = new StateListDrawable();
                    GradientDrawable unPressDrawable = DrawableFactory.getDrawable(typedArray);
                    unPressDrawable.setColor(color);
                    tmpDrawable.addState(new int[]{-android.R.attr.state_pressed}, drawable);
                    tmpDrawable.addState(new int[]{android.R.attr.state_pressed}, unPressDrawable);
                    view.setClickable(true);
                    setDrawable(tmpDrawable, view, otherTa);
                }
            }
            return view;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            typedArray.recycle();
            pressTa.recycle();
            selectorTa.recycle();
            textTa.recycle();
            buttonTa.recycle();
            otherTa.recycle();
        }

        return view;
    }

    private void setDrawable(Drawable drawable, View view, TypedArray otherTa){

        if(view instanceof TextView){
            if(otherTa.hasValue(R.styleable.bl_other_bl_position)){
                if(otherTa.getInt(R.styleable.bl_other_bl_position, 0) == 1){
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    ((TextView)view).setCompoundDrawables(drawable, null, null, null);
                }else if(otherTa.getInt(R.styleable.bl_other_bl_position, 0) == 2){
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    ((TextView)view).setCompoundDrawables(null, drawable, null, null);
                }else if(otherTa.getInt(R.styleable.bl_other_bl_position, 0) == 4){
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    ((TextView)view).setCompoundDrawables(null, null, drawable, null);
                }else if(otherTa.getInt(R.styleable.bl_other_bl_position, 0) == 8){
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    ((TextView)view).setCompoundDrawables(null, null, null, drawable);
                }
            }else {
                if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
                    view.setBackground(drawable);
                }else {
                    view.setBackgroundDrawable(drawable);
                }
            }
        }else {
            if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
                view.setBackground(drawable);
            }else {
                view.setBackgroundDrawable(drawable);
            }
        }

    }

    public void setInterceptFactory(LayoutInflater.Factory factory) {
        mViewCreateFactory = factory;
    }

    public void setInterceptFactory2(LayoutInflater.Factory2 factory) {
        mViewCreateFactory2 = factory;
    }

    private View createViewFromTag(Context context, String name, AttributeSet attrs) {
        if (name.equals("view")) {
            name = attrs.getAttributeValue(null, "class");
        }
        try {
            mConstructorArgs[0] = context;
            mConstructorArgs[1] = attrs;

            if (-1 == name.indexOf('.')) {
                View view = null;
                if ("View".equals(name)) {
                    view = createView(context, name, "android.view.");
                }
                if (view == null) {
                    view = createView(context, name, "android.widget.");
                }
                if (view == null) {
                    view = createView(context, name, "android.webkit.");
                }
                return view;
            } else {
                return createView(context, name, null);
            }
        } catch (Exception e) {
            Log.w("BackgroundLibrary", "cannot create 【" + name + "】 : ");
            return null;
        } finally {
            mConstructorArgs[0] = null;
            mConstructorArgs[1] = null;
        }
    }

    private View createView(Context context, String name, String prefix) throws InflateException {
        Constructor<? extends View> constructor = sConstructorMap.get(name);
        try {
            if (constructor == null) {
                Class<? extends View> clazz = context.getClassLoader().loadClass(
                        prefix != null ? (prefix + name) : name).asSubclass(View.class);

                constructor = clazz.getConstructor(sConstructorSignature);
                sConstructorMap.put(name, constructor);
            }
            constructor.setAccessible(true);
            return constructor.newInstance(mConstructorArgs);
        } catch (Exception e) {
            Log.w("BackgroundLibrary", "cannot create 【" + name + "】 : ");
            return null;
        }
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return onCreateView(name, context, attrs);
    }
}
