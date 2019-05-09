package com.noober.background.drawable;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;

import com.noober.background.R;

public class ColorStateCreator implements ICreateColorState{

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
            int attr = textTa.getIndex(i);
            if (attr == R.styleable.text_selector_bl_checkable_textColor) {
                setStateColor(textTa, attr, android.R.attr.state_checkable);
            } else if (attr == R.styleable.text_selector_bl_unCheckable_textColor) {
                setStateColor(textTa, attr, -android.R.attr.state_checkable);
            } else if (attr == R.styleable.text_selector_bl_checked_textColor) {
                setStateColor(textTa, attr, android.R.attr.state_checked);
            } else if (attr == R.styleable.text_selector_bl_unChecked_textColor) {
                setStateColor(textTa, attr, -android.R.attr.state_checked);
            } else if (attr == R.styleable.text_selector_bl_enabled_textColor) {
                setStateColor(textTa, attr, android.R.attr.state_enabled);
            } else if (attr == R.styleable.text_selector_bl_unEnabled_textColor) {
                setStateColor(textTa, attr, -android.R.attr.state_enabled);
            } else if (attr == R.styleable.text_selector_bl_selected_textColor) {
                setStateColor(textTa, attr, android.R.attr.state_selected);
            } else if (attr == R.styleable.text_selector_bl_unSelected_textColor) {
                setStateColor(textTa, attr, -android.R.attr.state_selected);
            } else if (attr == R.styleable.text_selector_bl_pressed_textColor) {
                setStateColor(textTa, attr, android.R.attr.state_pressed);
            } else if (attr == R.styleable.text_selector_bl_unPressed_textColor) {
                setStateColor(textTa, attr, -android.R.attr.state_pressed);
            } else if (attr == R.styleable.text_selector_bl_focused_textColor) {
                setStateColor(textTa, attr, android.R.attr.state_focused);
            } else if (attr == R.styleable.text_selector_bl_unFocused_textColor) {
                setStateColor(textTa, attr, -android.R.attr.state_focused);
            } else if (attr == R.styleable.text_selector_bl_activated_textColor) {
                setStateColor(textTa, attr, android.R.attr.state_activated);
            } else if (attr == R.styleable.text_selector_bl_unActivated_textColor) {
                setStateColor(textTa, attr, -android.R.attr.state_active);
            } else if (attr == R.styleable.text_selector_bl_active_textColor) {
                setStateColor(textTa, attr, android.R.attr.state_active);
            } else if (attr == R.styleable.text_selector_bl_unActive_textColor) {
                setStateColor(textTa, attr, -android.R.attr.state_activated);
            } else if (attr == R.styleable.text_selector_bl_expanded_textColor) {
                setStateColor(textTa, attr, android.R.attr.state_expanded);
            } else if (attr == R.styleable.text_selector_bl_unExpanded_textColor) {
                setStateColor(textTa, attr, -android.R.attr.state_expanded);
            }

        }
        return new ColorStateList(states, colors);
    }

    private void setStateColor(TypedArray selectorTa, int attr, @AttrRes int functionId) {
        states[index] = new int[]{functionId};
        colors[index] = selectorTa.getColor(attr, 0);
        index++;
    }
}
