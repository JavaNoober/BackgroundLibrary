package com.noober.background.drawable;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;

import com.noober.background.R;
import com.noober.background.utils.TypeValueHelper;

public class ColorStateCreator {

    private TypedArray textTa;

    ColorStateCreator(TypedArray textTa) {
        this.textTa = textTa;
    }

    private int[][] states = new int[][]{};
    private int[] colors = new int[]{};
    private int index;

    public ColorStateList create() {
        states = new int[textTa.getIndexCount()][];
        colors = new int[textTa.getIndexCount()];
        for (int i = 0; i < textTa.getIndexCount(); i++) {
            int attr = TypeValueHelper.sAppearanceTextValues.get(textTa.getIndex(i), -1);
            if (attr == -1) {
                continue;
            }
            int typeIndex = textTa.getIndex(i);

            if (attr == R.styleable.text_selector_bl_checkable_textColor) {
                setStateColor(textTa, typeIndex, android.R.attr.state_checkable);
            } else if (attr == R.styleable.text_selector_bl_unCheckable_textColor) {
                setStateColor(textTa, typeIndex, -android.R.attr.state_checkable);
            } else if (attr == R.styleable.text_selector_bl_checked_textColor) {
                setStateColor(textTa, typeIndex, android.R.attr.state_checked);
            } else if (attr == R.styleable.text_selector_bl_unChecked_textColor) {
                setStateColor(textTa, typeIndex, -android.R.attr.state_checked);
            } else if (attr == R.styleable.text_selector_bl_enabled_textColor) {
                setStateColor(textTa, typeIndex, android.R.attr.state_enabled);
            } else if (attr == R.styleable.text_selector_bl_unEnabled_textColor) {
                setStateColor(textTa, typeIndex, -android.R.attr.state_enabled);
            } else if (attr == R.styleable.text_selector_bl_selected_textColor) {
                setStateColor(textTa, typeIndex, android.R.attr.state_selected);
            } else if (attr == R.styleable.text_selector_bl_unSelected_textColor) {
                setStateColor(textTa, typeIndex, -android.R.attr.state_selected);
            } else if (attr == R.styleable.text_selector_bl_pressed_textColor) {
                setStateColor(textTa, typeIndex, android.R.attr.state_pressed);
            } else if (attr == R.styleable.text_selector_bl_unPressed_textColor) {
                setStateColor(textTa, typeIndex, -android.R.attr.state_pressed);
            } else if (attr == R.styleable.text_selector_bl_focused_textColor) {
                setStateColor(textTa, typeIndex, android.R.attr.state_focused);
            } else if (attr == R.styleable.text_selector_bl_unFocused_textColor) {
                setStateColor(textTa, typeIndex, -android.R.attr.state_focused);
            }
        }
        return new ColorStateList(states, colors);
    }

    private void setStateColor(TypedArray selectorTa, int typeIndex, @AttrRes int functionId) {
        states[index] = new int[]{functionId};
        colors[index] = selectorTa.getColor(typeIndex, 0);
        index++;
    }
}
