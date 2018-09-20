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
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import com.noober.background.drawable.DrawableFactory;

import java.lang.reflect.Constructor;
import java.util.Map;

public class BackgroundFactory implements LayoutInflater.Factory {

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
        try {
            if (typedArray.getIndexCount() == 0 && selectorTa.getIndexCount() == 0
                    && pressTa.getIndexCount() == 0 && textTa.getIndexCount() == 0) {
                return null;
            }
            if (view == null) {
                view = createViewFromTag(context, name, attrs);
            }
            if (view == null) {
                return null;
            }

            GradientDrawable drawable = null;
            StateListDrawable stateListDrawable = null;
            if (selectorTa.getIndexCount() > 0) {
                stateListDrawable = DrawableFactory.getSelectorDrawable(typedArray, selectorTa);
                view.setClickable(true);
                if (view instanceof RadioButton) {
                    ((RadioButton) view).setButtonDrawable(stateListDrawable);
                } else if (view instanceof CheckBox) {
                    ((CheckBox) view).setButtonDrawable(stateListDrawable);
                } else {
                    view.setBackground(stateListDrawable);
                }
            } else if (pressTa.getIndexCount() > 0) {
                drawable = DrawableFactory.getDrawable(typedArray);
                stateListDrawable = DrawableFactory.getPressDrawable(drawable, typedArray, pressTa);
                view.setClickable(true);
                view.setBackground(stateListDrawable);
            } else {
                drawable = DrawableFactory.getDrawable(typedArray);
                view.setBackground(drawable);
            }

            if (view instanceof TextView && textTa.getIndexCount() > 0) {
                ((TextView) view).setTextColor(DrawableFactory.getTextSelectorColor(textTa));
            }

            if (typedArray.getBoolean(R.styleable.background_ripple_enable, false) &&
                    typedArray.hasValue(R.styleable.background_ripple_color)) {
                int color = typedArray.getColor(R.styleable.background_ripple_color, 0);
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Drawable contentDrawable = (stateListDrawable == null ? drawable : stateListDrawable);
                    RippleDrawable rippleDrawable = new RippleDrawable(ColorStateList.valueOf(color), contentDrawable, contentDrawable);
                    view.setClickable(true);
                    view.setBackground(rippleDrawable);
                } else {
                    StateListDrawable tmpDrawable = new StateListDrawable();
                    GradientDrawable unPressDrawable = DrawableFactory.getDrawable(typedArray);
                    unPressDrawable.setColor(color);
                    tmpDrawable.addState(new int[]{-android.R.attr.state_pressed}, drawable);
                    tmpDrawable.addState(new int[]{android.R.attr.state_pressed}, unPressDrawable);
                    view.setClickable(true);
                    view.setBackground(tmpDrawable);
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
        }

        return view;
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
}
