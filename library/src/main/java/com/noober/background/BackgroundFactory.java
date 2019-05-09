package com.noober.background;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.noober.background.drawable.DrawableFactory;
import com.noober.background.view.Const;

import java.lang.reflect.Constructor;
import java.util.Map;

public class BackgroundFactory implements LayoutInflater.Factory2 {

    private LayoutInflater.Factory mViewCreateFactory;
    private LayoutInflater.Factory2 mViewCreateFactory2;

    private static final Class<?>[] sConstructorSignature = new Class[]{Context.class, AttributeSet.class};
    private static final Object[] mConstructorArgs = new Object[2];
    private static final Map<String, Constructor<? extends View>> sConstructorMap = new ArrayMap<>();

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        //如果是blview，代表已经进行了背景设置，无需再次创建，留给系统创建就行
        if(name.startsWith("com.noober.background.view")){
            return null;
        }
//        name = switchBLViewToOriginal(name);
        View view = null;

        //防止与其他调用factory库冲突，例如字体、皮肤替换库，用已经设置的factory来创建view
        if (mViewCreateFactory2 != null) {
            view = mViewCreateFactory2.onCreateView(name, context, attrs);
            if (view == null) {
                view = mViewCreateFactory2.onCreateView(null, name, context, attrs);
            }
        } else if (mViewCreateFactory != null) {
            view = mViewCreateFactory.onCreateView(name, context, attrs);
        }
        return setViewBackground(name, context, attrs, view);
    }

    private String switchBLViewToOriginal(String name) {
        if(name.equals(Const.BLButton)){
            name = "Button";
        }else if(name.equals(Const.BLCheckBox)){
            name = "CheckBox";
        }else if(name.equals(Const.BLEditText)){
            name = "EditText";
        }else if(name.equals(Const.BLFrameLayout)){
            name = "FrameLayout";
        }else if(name.equals(Const.BLGridLayout)){
            name = "GridLayout";
        }else if(name.equals(Const.BLGridView)){
            name = "GridView";
        }else if(name.equals(Const.BLImageButton)){
            name = "ImageButton";
        }else if(name.equals(Const.BLImageView)){
            name = "ImageView";
        }else if(name.equals(Const.BLLinearLayout)){
            name = "LinearLayout";
        }else if(name.equals(Const.BLListView)){
            name = "ListView";
        }else if(name.equals(Const.BLRadioButton)){
            name = "RadioButton";
        }else if(name.equals(Const.BLRadioGroup)){
            name = "RadioGroup";
        }else if(name.equals(Const.BLRelativeLayout)){
            name = "RelativeLayout";
        }else if(name.equals(Const.BLScrollView)){
            name = "ScrollView";
        }else if(name.equals(Const.BLTextView)){
            name = "TextView";
        }else if(name.equals(Const.BLView)){
            name = "View";
        }else if(name.equals(Const.BLConstraintLayout)){
            name = "android.support.constraint.ConstraintLayout";
        }
        return name;
    }

    @Nullable
    public static View setViewBackground(Context context, AttributeSet attrs, View view){
        return setViewBackground(null, context, attrs, view);
    }

    /**
     * 根据属性设置图片背景
     * @param name view的名字
     * @param context 上下文
     * @param attrs bl属性
     * @param view view
     * @return view
     */
    @Nullable
    private static View setViewBackground(String name, Context context, AttributeSet attrs, View view) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.background);
        TypedArray pressTa = context.obtainStyledAttributes(attrs, R.styleable.background_press);
        TypedArray selectorTa = context.obtainStyledAttributes(attrs, R.styleable.background_selector);
        TypedArray textTa = context.obtainStyledAttributes(attrs, R.styleable.text_selector);
        TypedArray buttonTa = context.obtainStyledAttributes(attrs, R.styleable.background_button_drawable);
        TypedArray otherTa = context.obtainStyledAttributes(attrs, R.styleable.bl_other);
        TypedArray animTa = context.obtainStyledAttributes(attrs, R.styleable.bl_anim);
        TypedArray multiSelTa = context.obtainStyledAttributes(attrs, R.styleable.background_multi_selector);
        TypedArray multiTextTa = context.obtainStyledAttributes(attrs, R.styleable.background_multi_selector_text);

        try {
            if (typedArray.getIndexCount() == 0 && selectorTa.getIndexCount() == 0 && pressTa.getIndexCount() == 0
                    && textTa.getIndexCount() == 0 && buttonTa.getIndexCount() == 0 && animTa.getIndexCount() == 0
                    && multiSelTa.getIndexCount() == 0 && multiTextTa.getIndexCount() == 0) {
                return view;
            }
            if (view == null) {
                view = createViewFromTag(context, name, attrs);
            }
            if (view == null) {
                return null;
            }
            //R.styleable.background_selector 和 R.styleable.background_multi_selector的属性不能同时使用
            if(selectorTa.getIndexCount() > 0 && multiSelTa.getIndexCount() > 0){
                throw new IllegalArgumentException("Background_selector and background_multi_selector cannot be used simultaneously");
            }
            if(textTa.getIndexCount() > 0 && multiTextTa.getIndexCount() > 0){
                throw new IllegalArgumentException("text_selector and background_multi_selector_text cannot be used simultaneously");
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
            } else if(animTa.getIndexCount() > 0){
                AnimationDrawable animationDrawable = DrawableFactory.getAnimationDrawable(animTa);
                setBackground(animationDrawable, view);
                if(animTa.getBoolean(R.styleable.bl_anim_bl_anim_auto_start, false)){
                    animationDrawable.start();
                }
            } else if(multiSelTa.getIndexCount() > 0){
                stateListDrawable = DrawableFactory.getMultiSelectorDrawable(context, multiSelTa);
                setBackground(stateListDrawable, view);
            }

            if (view instanceof TextView && textTa.getIndexCount() > 0) {
                ((TextView) view).setTextColor(DrawableFactory.getTextSelectorColor(textTa));
            }else if(view instanceof TextView && multiTextTa.getIndexCount() > 0){
                ((TextView) view).setTextColor(DrawableFactory.getMultiTextColorSelectorColorCreator(context, multiTextTa));
            }

            if (typedArray.getBoolean(R.styleable.background_bl_ripple_enable, false) &&
                    typedArray.hasValue(R.styleable.background_bl_ripple_color)) {
                int color = typedArray.getColor(R.styleable.background_bl_ripple_color, 0);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
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
            return view;
        } finally {
            typedArray.recycle();
            pressTa.recycle();
            selectorTa.recycle();
            textTa.recycle();
            buttonTa.recycle();
            otherTa.recycle();
            animTa.recycle();
            multiSelTa.recycle();
            multiTextTa.recycle();
        }
    }

    private static void setDrawable(Drawable drawable, View view, TypedArray otherTa){

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
                setBackground(drawable, view);
            }
        }else {
            setBackground(drawable, view);
        }

    }

    private static void setBackground(Drawable drawable, View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public void setInterceptFactory(LayoutInflater.Factory factory) {
        mViewCreateFactory = factory;
    }

    public void setInterceptFactory2(LayoutInflater.Factory2 factory) {
        mViewCreateFactory2 = factory;
    }

    private static View createViewFromTag(Context context, String name, AttributeSet attrs) {
        if(TextUtils.isEmpty(name)){
            return null;
        }
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

    private static View createView(Context context, String name, String prefix) throws InflateException {
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
