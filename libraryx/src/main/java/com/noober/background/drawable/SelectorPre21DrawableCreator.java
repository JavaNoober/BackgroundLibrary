package com.noober.background.drawable;

import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

import com.noober.background.R;

import androidx.annotation.AttrRes;
import androidx.annotation.StyleableRes;

public class SelectorPre21DrawableCreator implements ICreateDrawable {

    private TypedArray typedArray;

    @SuppressWarnings("WeakerAccess")
    public SelectorPre21DrawableCreator(TypedArray typedArray) {
        this.typedArray = typedArray;
    }

    @Override
    public StateListDrawable create() throws Exception {
        StateListDrawable stateListDrawable = new StateListDrawable();

        setSelectorDrawable(stateListDrawable, R.styleable.background_bl_checkable_solid_color, R.styleable.background_bl_checkable_stroke_color, android.R.attr.state_checkable);
        setSelectorDrawable(stateListDrawable, R.styleable.background_bl_unCheckable_solid_color, R.styleable.background_bl_unCheckable_stroke_color, -android.R.attr.state_checkable);
        setSelectorDrawable(stateListDrawable, R.styleable.background_bl_checked_solid_color, R.styleable.background_bl_checked_stroke_color, android.R.attr.state_checked);
        setSelectorDrawable(stateListDrawable, R.styleable.background_bl_unChecked_solid_color, R.styleable.background_bl_unChecked_stroke_color, -android.R.attr.state_checked);
        setSelectorDrawable(stateListDrawable, R.styleable.background_bl_enabled_solid_color, R.styleable.background_bl_enabled_stroke_color, android.R.attr.state_enabled);
        setSelectorDrawable(stateListDrawable, R.styleable.background_bl_unEnabled_solid_color, R.styleable.background_bl_unEnabled_stroke_color, -android.R.attr.state_enabled);
        setSelectorDrawable(stateListDrawable, R.styleable.background_bl_selected_solid_color, R.styleable.background_bl_selected_stroke_color, android.R.attr.state_selected);
        setSelectorDrawable(stateListDrawable, R.styleable.background_bl_unSelected_solid_color, R.styleable.background_bl_unSelected_stroke_color, -android.R.attr.state_selected);
        setSelectorDrawable(stateListDrawable, R.styleable.background_bl_pressed_solid_color, R.styleable.background_bl_pressed_stroke_color, android.R.attr.state_pressed);
        setSelectorDrawable(stateListDrawable, R.styleable.background_bl_unPressed_solid_color, R.styleable.background_bl_unPressed_stroke_color, -android.R.attr.state_pressed);
        setSelectorDrawable(stateListDrawable, R.styleable.background_bl_focused_solid_color, R.styleable.background_bl_focused_stroke_color, android.R.attr.state_focused);
        setSelectorDrawable(stateListDrawable, R.styleable.background_bl_unFocused_solid_color, R.styleable.background_bl_unFocused_stroke_color, -android.R.attr.state_focused);

        return stateListDrawable;
    }

    private void setSelectorDrawable(StateListDrawable stateListDrawable, @StyleableRes int solidAttr, @StyleableRes int strokeAttr, @AttrRes int functionId) throws Exception {
        if (typedArray.hasValue(solidAttr) || typedArray.hasValue(strokeAttr)) {
            GradientDrawable tmpDrawable = DrawableFactory.getDrawable(typedArray);
            if (typedArray.hasValue(solidAttr)) {
                tmpDrawable.setColor(typedArray.getColor(solidAttr, 0));
            }
            if (typedArray.hasValue(strokeAttr)) {
                int strokeWidth = typedArray.getDimensionPixelSize(R.styleable.background_bl_stroke_width, 0);
                int strokeColor = typedArray.getColor(strokeAttr, 0);
                float strokeDashWidth = typedArray.getDimension(R.styleable.background_bl_stroke_dashWidth, 0f);
                float strokeGap = typedArray.getDimension(R.styleable.background_bl_stroke_dashGap, 0f);
                tmpDrawable.setStroke(strokeWidth, strokeColor, strokeDashWidth, strokeGap);
            }
            stateListDrawable.addState(new int[]{functionId}, tmpDrawable);
        }
    }

}
