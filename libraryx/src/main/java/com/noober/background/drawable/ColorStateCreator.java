package com.noober.background.drawable;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;

import com.noober.background.R;

public class ColorStateCreator implements ICreateColorState {

    private static final StateColorListHelper.Dimension[] TEXT_DIMENSIONS = {
            new StateColorListHelper.Dimension(R.styleable.text_selector_bl_checkable_textColor,
                    R.styleable.text_selector_bl_unCheckable_textColor,
                    android.R.attr.state_checkable, -android.R.attr.state_checkable),
            new StateColorListHelper.Dimension(R.styleable.text_selector_bl_checked_textColor,
                    R.styleable.text_selector_bl_unChecked_textColor,
                    android.R.attr.state_checked, -android.R.attr.state_checked),
            new StateColorListHelper.Dimension(R.styleable.text_selector_bl_selected_textColor,
                    R.styleable.text_selector_bl_unSelected_textColor,
                    android.R.attr.state_selected, -android.R.attr.state_selected),
            new StateColorListHelper.Dimension(R.styleable.text_selector_bl_pressed_textColor,
                    R.styleable.text_selector_bl_unPressed_textColor,
                    android.R.attr.state_pressed, -android.R.attr.state_pressed),
            new StateColorListHelper.Dimension(R.styleable.text_selector_bl_focused_textColor,
                    R.styleable.text_selector_bl_unFocused_textColor,
                    android.R.attr.state_focused, -android.R.attr.state_focused),
            // activated / active 的 negState 保留历史实现的对调（unActivated -> -state_active，
            // unActive -> -state_activated），以保证历史布局渲染结果不变
            new StateColorListHelper.Dimension(R.styleable.text_selector_bl_activated_textColor,
                    R.styleable.text_selector_bl_unActivated_textColor,
                    android.R.attr.state_activated, -android.R.attr.state_active),
            new StateColorListHelper.Dimension(R.styleable.text_selector_bl_active_textColor,
                    R.styleable.text_selector_bl_unActive_textColor,
                    android.R.attr.state_active, -android.R.attr.state_activated),
            new StateColorListHelper.Dimension(R.styleable.text_selector_bl_expanded_textColor,
                    R.styleable.text_selector_bl_unExpanded_textColor,
                    android.R.attr.state_expanded, -android.R.attr.state_expanded),
    };

    private TypedArray textTa;

    ColorStateCreator(TypedArray textTa) {
        this.textTa = textTa;
    }

    public ColorStateList create() {
        ColorStateList result = StateColorListHelper.create(textTa,
                TEXT_DIMENSIONS, false,
                R.styleable.text_selector_bl_enabled_textColor, R.styleable.text_selector_bl_unEnabled_textColor,
                0);
        return result != null ? result : ColorStateList.valueOf(0);
    }
}