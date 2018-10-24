package com.noober.background.utils;

import android.util.SparseIntArray;

import com.noober.background.R;

public class TypeValueHelper {

    public static final SparseIntArray sAppearanceValues = new SparseIntArray();
    public static final SparseIntArray sAppearancePressValues = new SparseIntArray();
    public static final SparseIntArray sAppearanceSelectorValues = new SparseIntArray();
    public static final SparseIntArray sAppearanceTextValues = new SparseIntArray();
    static {
        sAppearanceValues.put(R.styleable.background_bl_shape, R.styleable.background_bl_shape);
        sAppearanceValues.put(R.styleable.background_bl_solid_color, R.styleable.background_bl_solid_color);
        sAppearanceValues.put(R.styleable.background_bl_corners_radius, R.styleable.background_bl_corners_radius);
        sAppearanceValues.put(R.styleable.background_bl_corners_bottomLeftRadius, R.styleable.background_bl_corners_bottomLeftRadius);
        sAppearanceValues.put(R.styleable.background_bl_corners_bottomRightRadius, R.styleable.background_bl_corners_bottomRightRadius);
        sAppearanceValues.put(R.styleable.background_bl_corners_topLeftRadius, R.styleable.background_bl_corners_topLeftRadius);
        sAppearanceValues.put(R.styleable.background_bl_corners_topRightRadius, R.styleable.background_bl_corners_topRightRadius);
        sAppearanceValues.put(R.styleable.background_bl_gradient_angle, R.styleable.background_bl_gradient_angle);
        sAppearanceValues.put(R.styleable.background_bl_gradient_centerX, R.styleable.background_bl_gradient_centerX);
        sAppearanceValues.put(R.styleable.background_bl_gradient_centerY, R.styleable.background_bl_gradient_centerY);
        sAppearanceValues.put(R.styleable.background_bl_gradient_centerColor, R.styleable.background_bl_gradient_centerColor);
        sAppearanceValues.put(R.styleable.background_bl_gradient_endColor, R.styleable.background_bl_gradient_endColor);
        sAppearanceValues.put(R.styleable.background_bl_gradient_startColor, R.styleable.background_bl_gradient_startColor);
        sAppearanceValues.put(R.styleable.background_bl_gradient_gradientRadius, R.styleable.background_bl_gradient_gradientRadius);
        sAppearanceValues.put(R.styleable.background_bl_gradient_type, R.styleable.background_bl_gradient_type);
        sAppearanceValues.put(R.styleable.background_bl_gradient_useLevel, R.styleable.background_bl_gradient_useLevel);
        sAppearanceValues.put(R.styleable.background_bl_padding_left, R.styleable.background_bl_padding_left);
        sAppearanceValues.put(R.styleable.background_bl_padding_top, R.styleable.background_bl_padding_top);
        sAppearanceValues.put(R.styleable.background_bl_padding_right, R.styleable.background_bl_padding_right);
        sAppearanceValues.put(R.styleable.background_bl_padding_bottom, R.styleable.background_bl_padding_bottom);
        sAppearanceValues.put(R.styleable.background_bl_size_width, R.styleable.background_bl_size_width);
        sAppearanceValues.put(R.styleable.background_bl_size_height, R.styleable.background_bl_size_height);
        sAppearanceValues.put(R.styleable.background_bl_stroke_width, R.styleable.background_bl_stroke_width);
        sAppearanceValues.put(R.styleable.background_bl_stroke_color, R.styleable.background_bl_stroke_color);
        sAppearanceValues.put(R.styleable.background_bl_stroke_dashWidth, R.styleable.background_bl_stroke_dashWidth);
        sAppearanceValues.put(R.styleable.background_bl_stroke_dashGap, R.styleable.background_bl_stroke_dashGap);
        sAppearanceValues.put(R.styleable.background_bl_ripple_enable, R.styleable.background_bl_ripple_enable);
        sAppearanceValues.put(R.styleable.background_bl_ripple_color, R.styleable.background_bl_ripple_color);
        sAppearanceValues.put(R.styleable.background_bl_checkable_stroke_color, R.styleable.background_bl_checkable_stroke_color);
        sAppearanceValues.put(R.styleable.background_bl_unCheckable_stroke_color, R.styleable.background_bl_unCheckable_stroke_color);
        sAppearanceValues.put(R.styleable.background_bl_checked_stroke_color, R.styleable.background_bl_checked_stroke_color);
        sAppearanceValues.put(R.styleable.background_bl_unChecked_stroke_color, R.styleable.background_bl_unChecked_stroke_color);
        sAppearanceValues.put(R.styleable.background_bl_enabled_stroke_color, R.styleable.background_bl_enabled_stroke_color);
        sAppearanceValues.put(R.styleable.background_bl_unEnabled_stroke_color, R.styleable.background_bl_unEnabled_stroke_color);
        sAppearanceValues.put(R.styleable.background_bl_selected_stroke_color, R.styleable.background_bl_selected_stroke_color);
        sAppearanceValues.put(R.styleable.background_bl_unSelected_stroke_color, R.styleable.background_bl_unSelected_stroke_color);
        sAppearanceValues.put(R.styleable.background_bl_pressed_stroke_color, R.styleable.background_bl_pressed_stroke_color);
        sAppearanceValues.put(R.styleable.background_bl_unPressed_stroke_color, R.styleable.background_bl_unPressed_stroke_color);
        sAppearanceValues.put(R.styleable.background_bl_focused_stroke_color, R.styleable.background_bl_focused_stroke_color);
        sAppearanceValues.put(R.styleable.background_bl_unFocused_stroke_color, R.styleable.background_bl_unFocused_stroke_color);

        sAppearancePressValues.put(R.styleable.background_press_bl_pressed_color, R.styleable.background_press_bl_pressed_color);
        sAppearancePressValues.put(R.styleable.background_press_bl_unpressed_color, R.styleable.background_press_bl_unpressed_color);

        sAppearanceSelectorValues.put(R.styleable.background_selector_bl_checkable_drawable, R.styleable.background_selector_bl_checkable_drawable);
        sAppearanceSelectorValues.put(R.styleable.background_selector_bl_checked_drawable, R.styleable.background_selector_bl_checked_drawable);
        sAppearanceSelectorValues.put(R.styleable.background_selector_bl_enabled_drawable, R.styleable.background_selector_bl_enabled_drawable);
        sAppearanceSelectorValues.put(R.styleable.background_selector_bl_selected_drawable, R.styleable.background_selector_bl_selected_drawable);
        sAppearanceSelectorValues.put(R.styleable.background_selector_bl_pressed_drawable, R.styleable.background_selector_bl_pressed_drawable);
        sAppearanceSelectorValues.put(R.styleable.background_selector_bl_focused_drawable, R.styleable.background_selector_bl_focused_drawable);
        sAppearanceSelectorValues.put(R.styleable.background_selector_bl_focused_hovered, R.styleable.background_selector_bl_focused_hovered);
        sAppearanceSelectorValues.put(R.styleable.background_selector_bl_focused_activated, R.styleable.background_selector_bl_focused_activated);
        sAppearanceSelectorValues.put(R.styleable.background_selector_bl_unCheckable_drawable, R.styleable.background_selector_bl_unCheckable_drawable);
        sAppearanceSelectorValues.put(R.styleable.background_selector_bl_unChecked_drawable, R.styleable.background_selector_bl_unChecked_drawable);
        sAppearanceSelectorValues.put(R.styleable.background_selector_bl_unEnabled_drawable, R.styleable.background_selector_bl_unEnabled_drawable);
        sAppearanceSelectorValues.put(R.styleable.background_selector_bl_unSelected_drawable, R.styleable.background_selector_bl_unSelected_drawable);
        sAppearanceSelectorValues.put(R.styleable.background_selector_bl_unPressed_drawable, R.styleable.background_selector_bl_unPressed_drawable);
        sAppearanceSelectorValues.put(R.styleable.background_selector_bl_unFocused_drawable, R.styleable.background_selector_bl_unFocused_drawable);
        sAppearanceSelectorValues.put(R.styleable.background_selector_bl_unFocused_hovered, R.styleable.background_selector_bl_unFocused_hovered);
        sAppearanceSelectorValues.put(R.styleable.background_selector_bl_unFocused_activated, R.styleable.background_selector_bl_unFocused_activated);

        sAppearanceTextValues.put(R.styleable.text_selector_bl_checkable_textColor, R.styleable.text_selector_bl_checkable_textColor);
        sAppearanceTextValues.put(R.styleable.text_selector_bl_checked_textColor, R.styleable.text_selector_bl_checked_textColor);
        sAppearanceTextValues.put(R.styleable.text_selector_bl_enabled_textColor, R.styleable.text_selector_bl_enabled_textColor);
        sAppearanceTextValues.put(R.styleable.text_selector_bl_selected_textColor, R.styleable.text_selector_bl_selected_textColor);
        sAppearanceTextValues.put(R.styleable.text_selector_bl_pressed_textColor, R.styleable.text_selector_bl_pressed_textColor);
        sAppearanceTextValues.put(R.styleable.text_selector_bl_focused_textColor, R.styleable.text_selector_bl_focused_textColor);
        sAppearanceTextValues.put(R.styleable.text_selector_bl_unCheckable_textColor, R.styleable.text_selector_bl_unCheckable_textColor);
        sAppearanceTextValues.put(R.styleable.text_selector_bl_unChecked_textColor, R.styleable.text_selector_bl_unChecked_textColor);
        sAppearanceTextValues.put(R.styleable.text_selector_bl_unEnabled_textColor, R.styleable.text_selector_bl_unEnabled_textColor);
        sAppearanceTextValues.put(R.styleable.text_selector_bl_unSelected_textColor, R.styleable.text_selector_bl_unSelected_textColor);
        sAppearanceTextValues.put(R.styleable.text_selector_bl_unPressed_textColor, R.styleable.text_selector_bl_unPressed_textColor);
        sAppearanceTextValues.put(R.styleable.text_selector_bl_unFocused_textColor, R.styleable.text_selector_bl_unFocused_textColor);
    }
}
