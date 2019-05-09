package com.noober.background.drawable;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

import org.xmlpull.v1.XmlPullParserException;

/**
 * Created by xiaoqi on 2018/9/12
 */
public class DrawableFactory {

    //获取shape属性的drawable
    public static GradientDrawable getDrawable(TypedArray typedArray) throws XmlPullParserException {
        return (GradientDrawable) new GradientDrawableCreator(typedArray).create();
    }

    //获取selector属性的drawable
    public static StateListDrawable getSelectorDrawable(TypedArray typedArray, TypedArray selectorTa) throws Exception {
        return (StateListDrawable) new SelectorDrawableCreator(typedArray, selectorTa).create();
    }

    //获取button 属性的drawable
    public static StateListDrawable getButtonDrawable(TypedArray typedArray, TypedArray buttonTa) throws Exception {
        return (StateListDrawable) new ButtonDrawableCreator(typedArray, buttonTa).create();
    }

    //获取text selector属性关于text的color
    public static ColorStateList getTextSelectorColor(TypedArray textTa) {
        return new ColorStateCreator(textTa).create();
    }

    //适配早期版本的属性
    public static StateListDrawable getPressDrawable(GradientDrawable drawable, TypedArray typedArray, TypedArray pressTa)
            throws Exception {
        return (StateListDrawable) new PressDrawableCreator(drawable, typedArray, pressTa).create();
    }

    //获取AnimationDrawable属性的drawable
    public static AnimationDrawable getAnimationDrawable(TypedArray animTa) throws Exception {
        return (AnimationDrawable) new AnimationDrawableCreator(animTa).create();
    }

    public static StateListDrawable getMultiSelectorDrawable(Context context, TypedArray selectorTa) {
        return (StateListDrawable) new MultiSelectorDrawableCreator(context, selectorTa).create();
    }

    public static ColorStateList getMultiTextColorSelectorColorCreator(Context context, TypedArray selectorTa) {
        return new MultiTextColorSelectorColorCreator(context, selectorTa).create();
    }

}
