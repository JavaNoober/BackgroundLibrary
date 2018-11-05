package com.noober.background.drawable;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.AttrRes;

import com.noober.background.R;

public class SelectorDrawableCreator implements ICreateDrawable {

    private TypedArray typedArray;
    private TypedArray selectorTa;

    public SelectorDrawableCreator(TypedArray typedArray, TypedArray selectorTa) {
        this.typedArray = typedArray;
        this.selectorTa = selectorTa;
    }

    @Override
    public Drawable create() throws Exception {
        StateListDrawable stateListDrawable = new StateListDrawable();

        for (int i = 0; i < selectorTa.getIndexCount(); i++) {
            int attr = selectorTa.getIndex(i);

            if (attr == R.styleable.background_selector_bl_checkable_drawable) {
                setSelectorDrawable(typedArray, selectorTa, stateListDrawable, attr, android.R.attr.state_checkable);
            } else if (attr == R.styleable.background_selector_bl_unCheckable_drawable) {
                setSelectorDrawable(typedArray, selectorTa, stateListDrawable, attr, -android.R.attr.state_checkable);
            } else if (attr == R.styleable.background_selector_bl_checked_drawable) {
                setSelectorDrawable(typedArray, selectorTa, stateListDrawable, attr, android.R.attr.state_checked);
            } else if (attr == R.styleable.background_selector_bl_unChecked_drawable) {
                setSelectorDrawable(typedArray, selectorTa, stateListDrawable, attr, -android.R.attr.state_checked);
            } else if (attr == R.styleable.background_selector_bl_enabled_drawable) {
                setSelectorDrawable(typedArray, selectorTa, stateListDrawable, attr, android.R.attr.state_enabled);
            } else if (attr == R.styleable.background_selector_bl_unEnabled_drawable) {
                setSelectorDrawable(typedArray, selectorTa, stateListDrawable, attr, -android.R.attr.state_enabled);
            } else if (attr == R.styleable.background_selector_bl_selected_drawable) {
                setSelectorDrawable(typedArray, selectorTa, stateListDrawable, attr, android.R.attr.state_selected);
            } else if (attr == R.styleable.background_selector_bl_unSelected_drawable) {
                setSelectorDrawable(typedArray, selectorTa, stateListDrawable, attr, -android.R.attr.state_selected);
            } else if (attr == R.styleable.background_selector_bl_pressed_drawable) {
                setSelectorDrawable(typedArray, selectorTa, stateListDrawable, attr, android.R.attr.state_pressed);
            } else if (attr == R.styleable.background_selector_bl_unPressed_drawable) {
                setSelectorDrawable(typedArray, selectorTa, stateListDrawable, attr, -android.R.attr.state_pressed);
            } else if (attr == R.styleable.background_selector_bl_focused_drawable) {
                setSelectorDrawable(typedArray, selectorTa, stateListDrawable, attr, android.R.attr.state_focused);
            } else if (attr == R.styleable.background_selector_bl_unFocused_drawable) {
                setSelectorDrawable(typedArray, selectorTa, stateListDrawable, attr, -android.R.attr.state_focused);
            } else if (attr == R.styleable.background_selector_bl_focused_hovered) {
                setSelectorDrawable(typedArray, selectorTa, stateListDrawable, attr, android.R.attr.state_hovered);
            } else if (attr == R.styleable.background_selector_bl_unFocused_hovered) {
                setSelectorDrawable(typedArray, selectorTa, stateListDrawable, attr, -android.R.attr.state_hovered);
            } else if (attr == R.styleable.background_selector_bl_focused_activated) {
                setSelectorDrawable(typedArray, selectorTa, stateListDrawable, attr, android.R.attr.state_activated);
            } else if (attr == R.styleable.background_selector_bl_unFocused_activated) {
                setSelectorDrawable(typedArray, selectorTa, stateListDrawable, attr, -android.R.attr.state_activated);
            }
        }
        return stateListDrawable;
    }

    private void setSelectorDrawable(TypedArray typedArray, TypedArray selectorTa,
                                     StateListDrawable stateListDrawable, int attr, @AttrRes int functionId) throws Exception {
        int color = 0;
        Drawable resDrawable = null;

        //这里用try catch先判断是否是颜色而不是直接调用getDrawable，为了方便填入的是颜色时可以沿用其他属性,
        //否则如果是其他资源会覆盖app:corners_radius等其他shape属性设置的效果
        try {
            color = selectorTa.getColor(attr, 0);
            if (color == 0) {
                resDrawable = selectorTa.getDrawable(attr);
            }
        } catch (Exception e) {
            resDrawable = selectorTa.getDrawable(attr);
        }
        if (resDrawable == null && color != 0) {
            GradientDrawable tmpDrawable = DrawableFactory.getDrawable(typedArray);
            tmpDrawable.setColor(color);
            stateListDrawable.addState(new int[]{functionId}, tmpDrawable);
        } else {
            stateListDrawable.addState(new int[]{functionId}, resDrawable);
        }
    }
}
