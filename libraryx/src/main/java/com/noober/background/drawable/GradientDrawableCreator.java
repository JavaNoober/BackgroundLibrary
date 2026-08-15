package com.noober.background.drawable;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;

import androidx.annotation.AttrRes;

import com.noober.background.R;

import org.xmlpull.v1.XmlPullParserException;

import java.lang.reflect.Field;

import static android.graphics.drawable.GradientDrawable.LINEAR_GRADIENT;

public class GradientDrawableCreator implements ICreateDrawable {

    private static final StateColorListHelper.Dimension[] SOLID_DIMENSIONS = {
            new StateColorListHelper.Dimension(R.styleable.background_bl_pressed_solid_color,
                    R.styleable.background_bl_unPressed_solid_color,
                    android.R.attr.state_pressed, -android.R.attr.state_pressed),
            new StateColorListHelper.Dimension(R.styleable.background_bl_checkable_solid_color,
                    R.styleable.background_bl_unCheckable_solid_color,
                    android.R.attr.state_checkable, -android.R.attr.state_checkable),
            new StateColorListHelper.Dimension(R.styleable.background_bl_checked_solid_color,
                    R.styleable.background_bl_unChecked_solid_color,
                    android.R.attr.state_checked, -android.R.attr.state_checked),
            new StateColorListHelper.Dimension(R.styleable.background_bl_selected_solid_color,
                    R.styleable.background_bl_unSelected_solid_color,
                    android.R.attr.state_selected, -android.R.attr.state_selected),
            new StateColorListHelper.Dimension(R.styleable.background_bl_focused_solid_color,
                    R.styleable.background_bl_unFocused_solid_color,
                    android.R.attr.state_focused, -android.R.attr.state_focused),
    };

    private static final StateColorListHelper.Dimension[] STROKE_DIMENSIONS = {
            new StateColorListHelper.Dimension(R.styleable.background_bl_pressed_stroke_color,
                    R.styleable.background_bl_unPressed_stroke_color,
                    android.R.attr.state_pressed, -android.R.attr.state_pressed),
            new StateColorListHelper.Dimension(R.styleable.background_bl_checkable_stroke_color,
                    R.styleable.background_bl_unCheckable_stroke_color,
                    android.R.attr.state_checkable, -android.R.attr.state_checkable),
            new StateColorListHelper.Dimension(R.styleable.background_bl_checked_stroke_color,
                    R.styleable.background_bl_unChecked_stroke_color,
                    android.R.attr.state_checked, -android.R.attr.state_checked),
            new StateColorListHelper.Dimension(R.styleable.background_bl_selected_stroke_color,
                    R.styleable.background_bl_unSelected_stroke_color,
                    android.R.attr.state_selected, -android.R.attr.state_selected),
            new StateColorListHelper.Dimension(R.styleable.background_bl_focused_stroke_color,
                    R.styleable.background_bl_unFocused_stroke_color,
                    android.R.attr.state_focused, -android.R.attr.state_focused),
    };

    private TypedArray typedArray;
    private boolean rtl;

    GradientDrawableCreator(TypedArray typedArray) {
        this(typedArray, false);
    }

    GradientDrawableCreator(TypedArray typedArray, boolean rtl) {
        this(typedArray, -1, rtl);
    }

    private int gradientState = -1;

    GradientDrawableCreator(TypedArray typedArray, @AttrRes int gradientState) {
        this(typedArray, gradientState, false);
    }

    GradientDrawableCreator(TypedArray typedArray, @AttrRes int gradientState, boolean rtl) {
        this.typedArray = typedArray;
        this.gradientState = gradientState;
        this.rtl = rtl;
    }

    @Override
    public GradientDrawable create() throws XmlPullParserException {
        GradientDrawable drawable = new GradientDrawable();
        float[] cornerRadius = new float[8];
        float sizeWidth = 0;
        float sizeHeight = 0;
        float strokeWidth = -1;
        float strokeDashWidth = 0;
        int strokeColor = 0;
        int solidColor = 0;
        float strokeGap = 0;
        float centerX = 0;
        float centerY = 0;
        int centerColor = 0;
        int startColor = 0;
        int endColor = 0;
        int gradientType = LINEAR_GRADIENT;
        int gradientAngle = 0;
        Rect padding = new Rect();
        // RTL 布局下 left/right 镜像：逻辑左上/右上/右下/左下 映射到物理角
        int tlIdx = rtl ? 2 : 0;
        int trIdx = rtl ? 0 : 2;
        int brIdx = rtl ? 6 : 4;
        int blIdx = rtl ? 4 : 6;
        for (int i = 0; i < typedArray.getIndexCount(); i++) {
            int attr = typedArray.getIndex(i);
            if (attr == R.styleable.background_bl_shape) {
                drawable.setShape(typedArray.getInt(attr, 0));
            } else if (attr == R.styleable.background_bl_solid_color) {
                solidColor = typedArray.getColor(attr, 0);
            } else if (attr == R.styleable.background_bl_corners_radius) {
                drawable.setCornerRadius(typedArray.getDimension(attr, 0));
            } else if (attr == R.styleable.background_bl_corners_bottomLeftRadius) {
                cornerRadius[blIdx] = typedArray.getDimension(attr, 0);
                cornerRadius[blIdx + 1] = cornerRadius[blIdx];
            } else if (attr == R.styleable.background_bl_corners_bottomRightRadius) {
                cornerRadius[brIdx] = typedArray.getDimension(attr, 0);
                cornerRadius[brIdx + 1] = cornerRadius[brIdx];
            } else if (attr == R.styleable.background_bl_corners_topLeftRadius) {
                cornerRadius[tlIdx] = typedArray.getDimension(attr, 0);
                cornerRadius[tlIdx + 1] = cornerRadius[tlIdx];
            } else if (attr == R.styleable.background_bl_corners_topRightRadius) {
                cornerRadius[trIdx] = typedArray.getDimension(attr, 0);
                cornerRadius[trIdx + 1] = cornerRadius[trIdx];
            } else if (attr == R.styleable.background_bl_corners_leftRadius) {
                cornerRadius[tlIdx] = typedArray.getDimension(attr, 0);
                cornerRadius[blIdx] = cornerRadius[tlIdx];
                cornerRadius[tlIdx + 1] = cornerRadius[tlIdx];
                cornerRadius[blIdx + 1] = cornerRadius[tlIdx];
            } else if (attr == R.styleable.background_bl_corners_topRadius) {
                cornerRadius[0] = typedArray.getDimension(attr, 0);
                cornerRadius[1] = typedArray.getDimension(attr, 0);
                cornerRadius[2] = typedArray.getDimension(attr, 0);
                cornerRadius[3] = typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.background_bl_corners_rightRadius) {
                cornerRadius[trIdx] = typedArray.getDimension(attr, 0);
                cornerRadius[brIdx] = cornerRadius[trIdx];
                cornerRadius[trIdx + 1] = cornerRadius[trIdx];
                cornerRadius[brIdx + 1] = cornerRadius[trIdx];
            } else if (attr == R.styleable.background_bl_corners_bottomRadius) {
                cornerRadius[4] = typedArray.getDimension(attr, 0);
                cornerRadius[5] = typedArray.getDimension(attr, 0);
                cornerRadius[6] = typedArray.getDimension(attr, 0);
                cornerRadius[7] = typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.background_bl_gradient_angle ||
                    attr == R.styleable.background_bl_checkable_gradient_angle ||
                    attr == R.styleable.background_bl_unCheckable_gradient_angle ||
                    attr == R.styleable.background_bl_checked_gradient_angle ||
                    attr == R.styleable.background_bl_unChecked_gradient_angle ||
                    attr == R.styleable.background_bl_enabled_gradient_angle ||
                    attr == R.styleable.background_bl_unEnabled_gradient_angle ||
                    attr == R.styleable.background_bl_selected_gradient_angle ||
                    attr == R.styleable.background_bl_unSelected_gradient_angle ||
                    attr == R.styleable.background_bl_pressed_gradient_angle ||
                    attr == R.styleable.background_bl_unPressed_gradient_angle ||
                    attr == R.styleable.background_bl_focused_gradient_angle ||
                    attr == R.styleable.background_bl_unFocused_gradient_angle) {

                if (gradientState == -1) {
                    gradientAngle = typedArray.getInteger(attr, 0);
                } else if (gradientState == android.R.attr.state_checkable &&
                        attr == R.styleable.background_bl_checkable_gradient_angle) {
                    gradientAngle = typedArray.getInteger(attr, 0);
                } else if (gradientState == -android.R.attr.state_checkable &&
                        attr == R.styleable.background_bl_unCheckable_gradient_angle) {
                    gradientAngle = typedArray.getInteger(attr, 0);
                } else if (gradientState == android.R.attr.state_checked &&
                        attr == R.styleable.background_bl_checked_gradient_angle) {
                    gradientAngle = typedArray.getInteger(attr, 0);
                } else if (gradientState == -android.R.attr.state_checked &&
                        attr == R.styleable.background_bl_unChecked_gradient_angle) {
                    gradientAngle = typedArray.getInteger(attr, 0);
                } else if (gradientState == android.R.attr.state_enabled &&
                        attr == R.styleable.background_bl_enabled_gradient_angle) {
                    gradientAngle = typedArray.getInteger(attr, 0);
                } else if (gradientState == -android.R.attr.state_enabled &&
                        attr == R.styleable.background_bl_unEnabled_gradient_angle) {
                    gradientAngle = typedArray.getInteger(attr, 0);
                } else if (gradientState == android.R.attr.state_selected &&
                        attr == R.styleable.background_bl_selected_gradient_angle) {
                    gradientAngle = typedArray.getInteger(attr, 0);
                } else if (gradientState == -android.R.attr.state_selected &&
                        attr == R.styleable.background_bl_unSelected_gradient_angle) {
                    gradientAngle = typedArray.getInteger(attr, 0);
                } else if (gradientState == android.R.attr.state_pressed &&
                        attr == R.styleable.background_bl_pressed_gradient_angle) {
                    gradientAngle = typedArray.getInteger(attr, 0);
                } else if (gradientState == -android.R.attr.state_pressed &&
                        attr == R.styleable.background_bl_unPressed_gradient_angle) {
                    gradientAngle = typedArray.getInteger(attr, 0);
                } else if (gradientState == android.R.attr.state_focused &&
                        attr == R.styleable.background_bl_focused_gradient_angle) {
                    gradientAngle = typedArray.getInteger(attr, 0);
                } else if (gradientState == -android.R.attr.state_focused &&
                        attr == R.styleable.background_bl_unFocused_gradient_angle) {
                    gradientAngle = typedArray.getInteger(attr, 0);
                }

            } else if (attr == R.styleable.background_bl_gradient_centerX ||
                    attr == R.styleable.background_bl_checkable_gradient_centerX ||
                    attr == R.styleable.background_bl_unCheckable_gradient_centerX ||
                    attr == R.styleable.background_bl_checked_gradient_centerX ||
                    attr == R.styleable.background_bl_unChecked_gradient_centerX ||
                    attr == R.styleable.background_bl_enabled_gradient_centerX ||
                    attr == R.styleable.background_bl_unEnabled_gradient_centerX ||
                    attr == R.styleable.background_bl_selected_gradient_centerX ||
                    attr == R.styleable.background_bl_unSelected_gradient_centerX ||
                    attr == R.styleable.background_bl_pressed_gradient_centerX ||
                    attr == R.styleable.background_bl_unPressed_gradient_centerX ||
                    attr == R.styleable.background_bl_focused_gradient_centerX ||
                    attr == R.styleable.background_bl_unFocused_gradient_centerX) {

                if (gradientState == -1) {
                    centerX = typedArray.getFloat(attr, -1);
                } else if (gradientState == android.R.attr.state_checkable &&
                        attr == R.styleable.background_bl_checkable_gradient_centerX) {
                    centerX = typedArray.getFloat(attr, -1);
                } else if (gradientState == -android.R.attr.state_checkable &&
                        attr == R.styleable.background_bl_unCheckable_gradient_centerX) {
                    centerX = typedArray.getFloat(attr, -1);
                } else if (gradientState == android.R.attr.state_checked &&
                        attr == R.styleable.background_bl_checked_gradient_centerX) {
                    centerX = typedArray.getFloat(attr, -1);
                } else if (gradientState == -android.R.attr.state_checked &&
                        attr == R.styleable.background_bl_unChecked_gradient_centerX) {
                    centerX = typedArray.getFloat(attr, -1);
                } else if (gradientState == android.R.attr.state_enabled &&
                        attr == R.styleable.background_bl_enabled_gradient_centerX) {
                    centerX = typedArray.getFloat(attr, -1);
                } else if (gradientState == -android.R.attr.state_enabled &&
                        attr == R.styleable.background_bl_unEnabled_gradient_centerX) {
                    centerX = typedArray.getFloat(attr, -1);
                } else if (gradientState == android.R.attr.state_selected &&
                        attr == R.styleable.background_bl_selected_gradient_centerX) {
                    centerX = typedArray.getFloat(attr, -1);
                } else if (gradientState == -android.R.attr.state_selected &&
                        attr == R.styleable.background_bl_unSelected_gradient_centerX) {
                    centerX = typedArray.getFloat(attr, -1);
                } else if (gradientState == android.R.attr.state_pressed &&
                        attr == R.styleable.background_bl_pressed_gradient_centerX) {
                    centerX = typedArray.getFloat(attr, -1);
                } else if (gradientState == -android.R.attr.state_pressed &&
                        attr == R.styleable.background_bl_unPressed_gradient_centerX) {
                    centerX = typedArray.getFloat(attr, -1);
                } else if (gradientState == android.R.attr.state_focused &&
                        attr == R.styleable.background_bl_focused_gradient_centerX) {
                    centerX = typedArray.getFloat(attr, -1);
                } else if (gradientState == -android.R.attr.state_focused &&
                        attr == R.styleable.background_bl_unFocused_gradient_centerX) {
                    centerX = typedArray.getFloat(attr, -1);
                }
            } else if (attr == R.styleable.background_bl_gradient_centerY ||
                    attr == R.styleable.background_bl_checkable_gradient_centerY ||
                    attr == R.styleable.background_bl_unCheckable_gradient_centerY ||
                    attr == R.styleable.background_bl_checked_gradient_centerY ||
                    attr == R.styleable.background_bl_unChecked_gradient_centerY ||
                    attr == R.styleable.background_bl_enabled_gradient_centerY ||
                    attr == R.styleable.background_bl_unEnabled_gradient_centerY ||
                    attr == R.styleable.background_bl_selected_gradient_centerY ||
                    attr == R.styleable.background_bl_unSelected_gradient_centerY ||
                    attr == R.styleable.background_bl_pressed_gradient_centerY ||
                    attr == R.styleable.background_bl_unPressed_gradient_centerY ||
                    attr == R.styleable.background_bl_focused_gradient_centerY ||
                    attr == R.styleable.background_bl_unFocused_gradient_centerY) {
                if (gradientState == -1) {
                    centerY = typedArray.getFloat(attr, -1);
                } else if (gradientState == android.R.attr.state_checkable &&
                        attr == R.styleable.background_bl_checkable_gradient_centerY) {
                    centerY = typedArray.getFloat(attr, -1);
                } else if (gradientState == -android.R.attr.state_checkable &&
                        attr == R.styleable.background_bl_unCheckable_gradient_centerY) {
                    centerY = typedArray.getFloat(attr, -1);
                } else if (gradientState == android.R.attr.state_checked &&
                        attr == R.styleable.background_bl_checked_gradient_centerY) {
                    centerY = typedArray.getFloat(attr, -1);
                } else if (gradientState == -android.R.attr.state_checked &&
                        attr == R.styleable.background_bl_unChecked_gradient_centerY) {
                    centerY = typedArray.getFloat(attr, -1);
                } else if (gradientState == android.R.attr.state_enabled &&
                        attr == R.styleable.background_bl_enabled_gradient_centerY) {
                    centerY = typedArray.getFloat(attr, -1);
                } else if (gradientState == -android.R.attr.state_enabled &&
                        attr == R.styleable.background_bl_unEnabled_gradient_centerY) {
                    centerY = typedArray.getFloat(attr, -1);
                } else if (gradientState == android.R.attr.state_selected &&
                        attr == R.styleable.background_bl_selected_gradient_centerY) {
                    centerY = typedArray.getFloat(attr, -1);
                } else if (gradientState == -android.R.attr.state_selected &&
                        attr == R.styleable.background_bl_unSelected_gradient_centerY) {
                    centerY = typedArray.getFloat(attr, -1);
                } else if (gradientState == android.R.attr.state_pressed &&
                        attr == R.styleable.background_bl_pressed_gradient_centerY) {
                    centerY = typedArray.getFloat(attr, -1);
                } else if (gradientState == -android.R.attr.state_pressed &&
                        attr == R.styleable.background_bl_unPressed_gradient_centerY) {
                    centerY = typedArray.getFloat(attr, -1);
                } else if (gradientState == android.R.attr.state_focused &&
                        attr == R.styleable.background_bl_focused_gradient_centerY) {
                    centerY = typedArray.getFloat(attr, -1);
                } else if (gradientState == -android.R.attr.state_focused &&
                        attr == R.styleable.background_bl_unFocused_gradient_centerY) {
                    centerY = typedArray.getFloat(attr, -1);
                }
            } else if (attr == R.styleable.background_bl_gradient_centerColor ||
                    attr == R.styleable.background_bl_checkable_gradient_centerColor ||
                    attr == R.styleable.background_bl_unCheckable_gradient_centerColor ||
                    attr == R.styleable.background_bl_checked_gradient_centerColor ||
                    attr == R.styleable.background_bl_unChecked_gradient_centerColor ||
                    attr == R.styleable.background_bl_enabled_gradient_centerColor ||
                    attr == R.styleable.background_bl_unEnabled_gradient_centerColor ||
                    attr == R.styleable.background_bl_selected_gradient_centerColor ||
                    attr == R.styleable.background_bl_unSelected_gradient_centerColor ||
                    attr == R.styleable.background_bl_pressed_gradient_centerColor ||
                    attr == R.styleable.background_bl_unPressed_gradient_centerColor ||
                    attr == R.styleable.background_bl_focused_gradient_centerColor ||
                    attr == R.styleable.background_bl_unFocused_gradient_centerColor) {
                if (gradientState == -1) {
                    centerColor = typedArray.getColor(attr, 0);
                } else if (gradientState == android.R.attr.state_checkable &&
                        attr == R.styleable.background_bl_checkable_gradient_centerColor) {
                    centerColor = typedArray.getColor(attr, 0);
                } else if (gradientState == -android.R.attr.state_checkable &&
                        attr == R.styleable.background_bl_unCheckable_gradient_centerColor) {
                    centerColor = typedArray.getColor(attr, 0);
                } else if (gradientState == android.R.attr.state_checked &&
                        attr == R.styleable.background_bl_checked_gradient_centerColor) {
                    centerColor = typedArray.getColor(attr, 0);
                } else if (gradientState == -android.R.attr.state_checked &&
                        attr == R.styleable.background_bl_unChecked_gradient_centerColor) {
                    centerColor = typedArray.getColor(attr, 0);
                } else if (gradientState == android.R.attr.state_enabled &&
                        attr == R.styleable.background_bl_enabled_gradient_centerColor) {
                    centerColor = typedArray.getColor(attr, 0);
                } else if (gradientState == -android.R.attr.state_enabled &&
                        attr == R.styleable.background_bl_unEnabled_gradient_centerColor) {
                    centerColor = typedArray.getColor(attr, 0);
                } else if (gradientState == android.R.attr.state_selected &&
                        attr == R.styleable.background_bl_selected_gradient_centerColor) {
                    centerColor = typedArray.getColor(attr, 0);
                } else if (gradientState == -android.R.attr.state_selected &&
                        attr == R.styleable.background_bl_unSelected_gradient_centerColor) {
                    centerColor = typedArray.getColor(attr, 0);
                } else if (gradientState == android.R.attr.state_pressed &&
                        attr == R.styleable.background_bl_pressed_gradient_centerColor) {
                    centerColor = typedArray.getColor(attr, 0);
                } else if (gradientState == -android.R.attr.state_pressed &&
                        attr == R.styleable.background_bl_unPressed_gradient_centerColor) {
                    centerColor = typedArray.getColor(attr, 0);
                } else if (gradientState == android.R.attr.state_focused &&
                        attr == R.styleable.background_bl_focused_gradient_centerColor) {
                    centerColor = typedArray.getColor(attr, 0);
                } else if (gradientState == -android.R.attr.state_focused &&
                        attr == R.styleable.background_bl_unFocused_gradient_centerColor) {
                    centerColor = typedArray.getColor(attr, 0);
                }

            } else if (attr == R.styleable.background_bl_gradient_endColor ||
                    attr == R.styleable.background_bl_checkable_gradient_endColor ||
                    attr == R.styleable.background_bl_unCheckable_gradient_endColor ||
                    attr == R.styleable.background_bl_checked_gradient_endColor ||
                    attr == R.styleable.background_bl_unChecked_gradient_endColor ||
                    attr == R.styleable.background_bl_enabled_gradient_endColor ||
                    attr == R.styleable.background_bl_unEnabled_gradient_endColor ||
                    attr == R.styleable.background_bl_selected_gradient_endColor ||
                    attr == R.styleable.background_bl_unSelected_gradient_endColor ||
                    attr == R.styleable.background_bl_pressed_gradient_endColor ||
                    attr == R.styleable.background_bl_unPressed_gradient_endColor ||
                    attr == R.styleable.background_bl_focused_gradient_endColor ||
                    attr == R.styleable.background_bl_unFocused_gradient_endColor) {
                if (gradientState == -1) {
                    endColor = typedArray.getColor(attr, 0);
                } else if (gradientState == android.R.attr.state_checkable &&
                        attr == R.styleable.background_bl_checkable_gradient_endColor) {
                    endColor = typedArray.getColor(attr, 0);
                } else if (gradientState == -android.R.attr.state_checkable &&
                        attr == R.styleable.background_bl_unCheckable_gradient_endColor) {
                    endColor = typedArray.getColor(attr, 0);
                } else if (gradientState == android.R.attr.state_checked &&
                        attr == R.styleable.background_bl_checked_gradient_endColor) {
                    endColor = typedArray.getColor(attr, 0);
                } else if (gradientState == -android.R.attr.state_checked &&
                        attr == R.styleable.background_bl_unChecked_gradient_endColor) {
                    endColor = typedArray.getColor(attr, 0);
                } else if (gradientState == android.R.attr.state_enabled &&
                        attr == R.styleable.background_bl_enabled_gradient_endColor) {
                    endColor = typedArray.getColor(attr, 0);
                } else if (gradientState == -android.R.attr.state_enabled &&
                        attr == R.styleable.background_bl_unEnabled_gradient_endColor) {
                    endColor = typedArray.getColor(attr, 0);
                } else if (gradientState == android.R.attr.state_selected &&
                        attr == R.styleable.background_bl_selected_gradient_endColor) {
                    endColor = typedArray.getColor(attr, 0);
                } else if (gradientState == -android.R.attr.state_selected &&
                        attr == R.styleable.background_bl_unSelected_gradient_endColor) {
                    endColor = typedArray.getColor(attr, 0);
                } else if (gradientState == android.R.attr.state_pressed &&
                        attr == R.styleable.background_bl_pressed_gradient_endColor) {
                    endColor = typedArray.getColor(attr, 0);
                } else if (gradientState == -android.R.attr.state_pressed &&
                        attr == R.styleable.background_bl_unPressed_gradient_endColor) {
                    endColor = typedArray.getColor(attr, 0);
                } else if (gradientState == android.R.attr.state_focused &&
                        attr == R.styleable.background_bl_focused_gradient_endColor) {
                    endColor = typedArray.getColor(attr, 0);
                } else if (gradientState == -android.R.attr.state_focused &&
                        attr == R.styleable.background_bl_unFocused_gradient_endColor) {
                    endColor = typedArray.getColor(attr, 0);
                }
            } else if (attr == R.styleable.background_bl_gradient_startColor ||
                    attr == R.styleable.background_bl_checkable_gradient_startColor ||
                    attr == R.styleable.background_bl_unCheckable_gradient_startColor ||
                    attr == R.styleable.background_bl_checked_gradient_startColor ||
                    attr == R.styleable.background_bl_unChecked_gradient_startColor ||
                    attr == R.styleable.background_bl_enabled_gradient_startColor ||
                    attr == R.styleable.background_bl_unEnabled_gradient_startColor ||
                    attr == R.styleable.background_bl_selected_gradient_startColor ||
                    attr == R.styleable.background_bl_unSelected_gradient_startColor ||
                    attr == R.styleable.background_bl_pressed_gradient_startColor ||
                    attr == R.styleable.background_bl_unPressed_gradient_startColor ||
                    attr == R.styleable.background_bl_focused_gradient_startColor ||
                    attr == R.styleable.background_bl_unFocused_gradient_startColor) {
                if (gradientState == -1) {
                    startColor = typedArray.getColor(attr, 0);
                } else if (gradientState == android.R.attr.state_checkable &&
                        attr == R.styleable.background_bl_checkable_gradient_startColor) {
                    startColor = typedArray.getColor(attr, 0);
                } else if (gradientState == -android.R.attr.state_checkable &&
                        attr == R.styleable.background_bl_unCheckable_gradient_startColor) {
                    startColor = typedArray.getColor(attr, 0);
                } else if (gradientState == android.R.attr.state_checked &&
                        attr == R.styleable.background_bl_checked_gradient_startColor) {
                    startColor = typedArray.getColor(attr, 0);
                } else if (gradientState == -android.R.attr.state_checked &&
                        attr == R.styleable.background_bl_unChecked_gradient_startColor) {
                    startColor = typedArray.getColor(attr, 0);
                } else if (gradientState == android.R.attr.state_enabled &&
                        attr == R.styleable.background_bl_enabled_gradient_startColor) {
                    startColor = typedArray.getColor(attr, 0);
                } else if (gradientState == -android.R.attr.state_enabled &&
                        attr == R.styleable.background_bl_unEnabled_gradient_startColor) {
                    startColor = typedArray.getColor(attr, 0);
                } else if (gradientState == android.R.attr.state_selected &&
                        attr == R.styleable.background_bl_selected_gradient_startColor) {
                    startColor = typedArray.getColor(attr, 0);
                } else if (gradientState == -android.R.attr.state_selected &&
                        attr == R.styleable.background_bl_unSelected_gradient_startColor) {
                    startColor = typedArray.getColor(attr, 0);
                } else if (gradientState == android.R.attr.state_pressed &&
                        attr == R.styleable.background_bl_pressed_gradient_startColor) {
                    startColor = typedArray.getColor(attr, 0);
                } else if (gradientState == -android.R.attr.state_pressed &&
                        attr == R.styleable.background_bl_unPressed_gradient_startColor) {
                    startColor = typedArray.getColor(attr, 0);
                } else if (gradientState == android.R.attr.state_focused &&
                        attr == R.styleable.background_bl_focused_gradient_startColor) {
                    startColor = typedArray.getColor(attr, 0);
                } else if (gradientState == -android.R.attr.state_focused &&
                        attr == R.styleable.background_bl_unFocused_gradient_startColor) {
                    startColor = typedArray.getColor(attr, 0);
                }
            } else if (attr == R.styleable.background_bl_gradient_gradientRadius ||
                    attr == R.styleable.background_bl_checkable_gradient_gradientRadius ||
                    attr == R.styleable.background_bl_unCheckable_gradient_gradientRadius ||
                    attr == R.styleable.background_bl_checked_gradient_gradientRadius ||
                    attr == R.styleable.background_bl_unChecked_gradient_gradientRadius ||
                    attr == R.styleable.background_bl_enabled_gradient_gradientRadius ||
                    attr == R.styleable.background_bl_unEnabled_gradient_gradientRadius ||
                    attr == R.styleable.background_bl_selected_gradient_gradientRadius ||
                    attr == R.styleable.background_bl_unSelected_gradient_gradientRadius ||
                    attr == R.styleable.background_bl_pressed_gradient_gradientRadius ||
                    attr == R.styleable.background_bl_unPressed_gradient_gradientRadius ||
                    attr == R.styleable.background_bl_focused_gradient_gradientRadius ||
                    attr == R.styleable.background_bl_unFocused_gradient_gradientRadius) {
                if (gradientState == -1) {
                    drawable.setGradientRadius(typedArray.getDimension(attr, 0));
                } else if (gradientState == android.R.attr.state_checkable &&
                        attr == R.styleable.background_bl_checkable_gradient_gradientRadius) {
                    drawable.setGradientRadius(typedArray.getDimension(attr, 0));
                } else if (gradientState == -android.R.attr.state_checkable &&
                        attr == R.styleable.background_bl_unCheckable_gradient_gradientRadius) {
                    drawable.setGradientRadius(typedArray.getDimension(attr, 0));
                } else if (gradientState == android.R.attr.state_checked &&
                        attr == R.styleable.background_bl_checked_gradient_gradientRadius) {
                    drawable.setGradientRadius(typedArray.getDimension(attr, 0));
                } else if (gradientState == -android.R.attr.state_checked &&
                        attr == R.styleable.background_bl_unChecked_gradient_gradientRadius) {
                    drawable.setGradientRadius(typedArray.getDimension(attr, 0));
                } else if (gradientState == android.R.attr.state_enabled &&
                        attr == R.styleable.background_bl_enabled_gradient_gradientRadius) {
                    drawable.setGradientRadius(typedArray.getDimension(attr, 0));
                } else if (gradientState == -android.R.attr.state_enabled &&
                        attr == R.styleable.background_bl_unEnabled_gradient_gradientRadius) {
                    drawable.setGradientRadius(typedArray.getDimension(attr, 0));
                } else if (gradientState == android.R.attr.state_selected &&
                        attr == R.styleable.background_bl_selected_gradient_gradientRadius) {
                    drawable.setGradientRadius(typedArray.getDimension(attr, 0));
                } else if (gradientState == -android.R.attr.state_selected &&
                        attr == R.styleable.background_bl_unSelected_gradient_gradientRadius) {
                    drawable.setGradientRadius(typedArray.getDimension(attr, 0));
                } else if (gradientState == android.R.attr.state_pressed &&
                        attr == R.styleable.background_bl_pressed_gradient_gradientRadius) {
                    drawable.setGradientRadius(typedArray.getDimension(attr, 0));
                } else if (gradientState == -android.R.attr.state_pressed &&
                        attr == R.styleable.background_bl_unPressed_gradient_gradientRadius) {
                    drawable.setGradientRadius(typedArray.getDimension(attr, 0));
                } else if (gradientState == android.R.attr.state_focused &&
                        attr == R.styleable.background_bl_focused_gradient_gradientRadius) {
                    drawable.setGradientRadius(typedArray.getDimension(attr, 0));
                } else if (gradientState == -android.R.attr.state_focused &&
                        attr == R.styleable.background_bl_unFocused_gradient_gradientRadius) {
                    drawable.setGradientRadius(typedArray.getDimension(attr, 0));
                }

            } else if (attr == R.styleable.background_bl_gradient_type ||
                    attr == R.styleable.background_bl_checkable_gradient_type ||
                    attr == R.styleable.background_bl_unCheckable_gradient_type ||
                    attr == R.styleable.background_bl_checked_gradient_type ||
                    attr == R.styleable.background_bl_unChecked_gradient_type ||
                    attr == R.styleable.background_bl_enabled_gradient_type ||
                    attr == R.styleable.background_bl_unEnabled_gradient_type ||
                    attr == R.styleable.background_bl_selected_gradient_type ||
                    attr == R.styleable.background_bl_unSelected_gradient_type ||
                    attr == R.styleable.background_bl_pressed_gradient_type ||
                    attr == R.styleable.background_bl_unPressed_gradient_type ||
                    attr == R.styleable.background_bl_focused_gradient_type ||
                    attr == R.styleable.background_bl_unFocused_gradient_type) {
                if (gradientState == -1) {
                    gradientType = typedArray.getInt(attr, 0);
                } else if (gradientState == android.R.attr.state_checkable &&
                        attr == R.styleable.background_bl_checkable_gradient_type) {
                    gradientType = typedArray.getInt(attr, 0);
                } else if (gradientState == -android.R.attr.state_checkable &&
                        attr == R.styleable.background_bl_unCheckable_gradient_type) {
                    gradientType = typedArray.getInt(attr, 0);
                } else if (gradientState == android.R.attr.state_checked &&
                        attr == R.styleable.background_bl_checked_gradient_type) {
                    gradientType = typedArray.getInt(attr, 0);
                } else if (gradientState == -android.R.attr.state_checked &&
                        attr == R.styleable.background_bl_unChecked_gradient_type) {
                    gradientType = typedArray.getInt(attr, 0);
                } else if (gradientState == android.R.attr.state_enabled &&
                        attr == R.styleable.background_bl_enabled_gradient_type) {
                    gradientType = typedArray.getInt(attr, 0);
                } else if (gradientState == -android.R.attr.state_enabled &&
                        attr == R.styleable.background_bl_unEnabled_gradient_type) {
                    gradientType = typedArray.getInt(attr, 0);
                } else if (gradientState == android.R.attr.state_selected &&
                        attr == R.styleable.background_bl_selected_gradient_type) {
                    gradientType = typedArray.getInt(attr, 0);
                } else if (gradientState == -android.R.attr.state_selected &&
                        attr == R.styleable.background_bl_unSelected_gradient_type) {
                    gradientType = typedArray.getInt(attr, 0);
                } else if (gradientState == android.R.attr.state_pressed &&
                        attr == R.styleable.background_bl_pressed_gradient_type) {
                    gradientType = typedArray.getInt(attr, 0);
                } else if (gradientState == -android.R.attr.state_pressed &&
                        attr == R.styleable.background_bl_unPressed_gradient_type) {
                    gradientType = typedArray.getInt(attr, 0);
                } else if (gradientState == android.R.attr.state_focused &&
                        attr == R.styleable.background_bl_focused_gradient_type) {
                    gradientType = typedArray.getInt(attr, 0);
                } else if (gradientState == -android.R.attr.state_focused &&
                        attr == R.styleable.background_bl_unFocused_gradient_type) {
                    gradientType = typedArray.getInt(attr, 0);
                }
                drawable.setGradientType(gradientType);
            } else if (attr == R.styleable.background_bl_gradient_useLevel ||
                    attr == R.styleable.background_bl_checkable_gradient_useLevel ||
                    attr == R.styleable.background_bl_unCheckable_gradient_useLevel ||
                    attr == R.styleable.background_bl_checked_gradient_useLevel ||
                    attr == R.styleable.background_bl_unChecked_gradient_useLevel ||
                    attr == R.styleable.background_bl_enabled_gradient_useLevel ||
                    attr == R.styleable.background_bl_unEnabled_gradient_useLevel ||
                    attr == R.styleable.background_bl_selected_gradient_useLevel ||
                    attr == R.styleable.background_bl_unSelected_gradient_useLevel ||
                    attr == R.styleable.background_bl_pressed_gradient_useLevel ||
                    attr == R.styleable.background_bl_unPressed_gradient_useLevel ||
                    attr == R.styleable.background_bl_focused_gradient_useLevel ||
                    attr == R.styleable.background_bl_unFocused_gradient_useLevel) {
                if (gradientState == -1) {
                    drawable.setUseLevel(typedArray.getBoolean(attr, false));
                } else if (gradientState == android.R.attr.state_checkable &&
                        attr == R.styleable.background_bl_checkable_gradient_useLevel) {
                    drawable.setUseLevel(typedArray.getBoolean(attr, false));
                } else if (gradientState == -android.R.attr.state_checkable &&
                        attr == R.styleable.background_bl_unCheckable_gradient_useLevel) {
                    drawable.setUseLevel(typedArray.getBoolean(attr, false));
                } else if (gradientState == android.R.attr.state_checked &&
                        attr == R.styleable.background_bl_checked_gradient_useLevel) {
                    drawable.setUseLevel(typedArray.getBoolean(attr, false));
                } else if (gradientState == -android.R.attr.state_checked &&
                        attr == R.styleable.background_bl_unChecked_gradient_useLevel) {
                    drawable.setUseLevel(typedArray.getBoolean(attr, false));
                } else if (gradientState == android.R.attr.state_enabled &&
                        attr == R.styleable.background_bl_enabled_gradient_useLevel) {
                    drawable.setUseLevel(typedArray.getBoolean(attr, false));
                } else if (gradientState == -android.R.attr.state_enabled &&
                        attr == R.styleable.background_bl_unEnabled_gradient_useLevel) {
                    drawable.setUseLevel(typedArray.getBoolean(attr, false));
                } else if (gradientState == android.R.attr.state_selected &&
                        attr == R.styleable.background_bl_selected_gradient_useLevel) {
                    drawable.setUseLevel(typedArray.getBoolean(attr, false));
                } else if (gradientState == -android.R.attr.state_selected &&
                        attr == R.styleable.background_bl_unSelected_gradient_useLevel) {
                    drawable.setUseLevel(typedArray.getBoolean(attr, false));
                } else if (gradientState == android.R.attr.state_pressed &&
                        attr == R.styleable.background_bl_pressed_gradient_useLevel) {
                    drawable.setUseLevel(typedArray.getBoolean(attr, false));
                } else if (gradientState == -android.R.attr.state_pressed &&
                        attr == R.styleable.background_bl_unPressed_gradient_useLevel) {
                    drawable.setUseLevel(typedArray.getBoolean(attr, false));
                } else if (gradientState == android.R.attr.state_focused &&
                        attr == R.styleable.background_bl_focused_gradient_useLevel) {
                    drawable.setUseLevel(typedArray.getBoolean(attr, false));
                } else if (gradientState == -android.R.attr.state_focused &&
                        attr == R.styleable.background_bl_unFocused_gradient_useLevel) {
                    drawable.setUseLevel(typedArray.getBoolean(attr, false));
                }

            } else if (attr == R.styleable.background_bl_padding_left) {
                if (rtl) {
                    padding.right = (int) typedArray.getDimension(attr, 0);
                } else {
                    padding.left = (int) typedArray.getDimension(attr, 0);
                }
            } else if (attr == R.styleable.background_bl_padding_top) {
                padding.top = (int) typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.background_bl_padding_right) {
                if (rtl) {
                    padding.left = (int) typedArray.getDimension(attr, 0);
                } else {
                    padding.right = (int) typedArray.getDimension(attr, 0);
                }
            } else if (attr == R.styleable.background_bl_padding_bottom) {
                padding.bottom = (int) typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.background_bl_size_width) {
                sizeWidth = typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.background_bl_size_height) {
                sizeHeight = typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.background_bl_stroke_width) {
                strokeWidth = typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.background_bl_stroke_color) {
                strokeColor = typedArray.getColor(attr, 0);
            } else if (attr == R.styleable.background_bl_stroke_dashWidth) {
                strokeDashWidth = typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.background_bl_stroke_dashGap) {
                strokeGap = typedArray.getDimension(attr, 0);
            }
        }
        if (hasSetRadius(cornerRadius)) {
            drawable.setCornerRadii(cornerRadius);
        }
        if (typedArray.hasValue(R.styleable.background_bl_size_width) &&
                typedArray.hasValue(R.styleable.background_bl_size_height)) {
            drawable.setSize((int) sizeWidth, (int) sizeHeight);
        }
        //设置填充颜色
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ColorStateList colorStateList = StateColorListHelper.create(typedArray,
                    SOLID_DIMENSIONS, true,
                    R.styleable.background_bl_enabled_solid_color, R.styleable.background_bl_unEnabled_solid_color,
                    R.styleable.background_bl_solid_color);
            if (colorStateList != null) {
                drawable.setColor(colorStateList);
            } else if (typedArray.hasValue(R.styleable.background_bl_solid_color)) {
                drawable.setColor(solidColor);
            }
        } else if (typedArray.hasValue(R.styleable.background_bl_solid_color)) {
            drawable.setColor(solidColor);
        }

        //设置边框颜色（设置了渐变描边时跳过纯色描边，交由 createStrokeGradientDrawable 生成）
        boolean strokeGradient = typedArray.hasValue(R.styleable.background_bl_stroke_gradient_startColor)
                && typedArray.hasValue(R.styleable.background_bl_stroke_gradient_endColor);
        if (typedArray.hasValue(R.styleable.background_bl_stroke_width) && !strokeGradient) {
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ColorStateList colorStateList = StateColorListHelper.create(typedArray,
                        STROKE_DIMENSIONS, true,
                        R.styleable.background_bl_enabled_stroke_color, R.styleable.background_bl_unEnabled_stroke_color,
                        R.styleable.background_bl_stroke_color);
                if (colorStateList != null) {
                    drawable.setStroke((int) strokeWidth, colorStateList, strokeDashWidth, strokeGap);
                } else if (typedArray.hasValue(R.styleable.background_bl_stroke_color)) {
                    drawable.setStroke((int) strokeWidth, strokeColor, strokeDashWidth, strokeGap);
                }
            } else if (typedArray.hasValue(R.styleable.background_bl_stroke_color)) {
                drawable.setStroke((int) strokeWidth, strokeColor, strokeDashWidth, strokeGap);
            }
        }

        if ((typedArray.hasValue(R.styleable.background_bl_gradient_centerX) &&
                typedArray.hasValue(R.styleable.background_bl_gradient_centerY)) ||
                (typedArray.hasValue(R.styleable.background_bl_checkable_gradient_centerX) &&
                        typedArray.hasValue(R.styleable.background_bl_unCheckable_gradient_centerY)) ||
                (typedArray.hasValue(R.styleable.background_bl_checked_gradient_centerX) &&
                        typedArray.hasValue(R.styleable.background_bl_unChecked_gradient_centerY)) ||
                (typedArray.hasValue(R.styleable.background_bl_enabled_gradient_centerX) &&
                        typedArray.hasValue(R.styleable.background_bl_unEnabled_gradient_centerY)) ||
                (typedArray.hasValue(R.styleable.background_bl_selected_gradient_centerX) &&
                        typedArray.hasValue(R.styleable.background_bl_unSelected_gradient_centerY)) ||
                (typedArray.hasValue(R.styleable.background_bl_pressed_gradient_centerX) &&
                        typedArray.hasValue(R.styleable.background_bl_unPressed_gradient_centerY)) ||
                (typedArray.hasValue(R.styleable.background_bl_focused_gradient_centerX) &&
                        typedArray.hasValue(R.styleable.background_bl_unFocused_gradient_centerY))) {
            drawable.setGradientCenter(centerX, centerY);
        }

        if (((typedArray.hasValue(R.styleable.background_bl_gradient_startColor) &&
                typedArray.hasValue(R.styleable.background_bl_gradient_endColor)) ||
                (typedArray.hasValue(R.styleable.background_bl_checkable_gradient_startColor) &&
                        typedArray.hasValue(R.styleable.background_bl_unCheckable_gradient_endColor)) ||
                (typedArray.hasValue(R.styleable.background_bl_checked_gradient_startColor) &&
                        typedArray.hasValue(R.styleable.background_bl_unChecked_gradient_endColor)) ||
                (typedArray.hasValue(R.styleable.background_bl_enabled_gradient_startColor) &&
                        typedArray.hasValue(R.styleable.background_bl_unEnabled_gradient_endColor)) ||
                (typedArray.hasValue(R.styleable.background_bl_selected_gradient_startColor) &&
                        typedArray.hasValue(R.styleable.background_bl_unSelected_gradient_endColor)) ||
                (typedArray.hasValue(R.styleable.background_bl_pressed_gradient_startColor) &&
                        typedArray.hasValue(R.styleable.background_bl_unPressed_gradient_endColor)) ||
                (typedArray.hasValue(R.styleable.background_bl_focused_gradient_startColor) &&
                        typedArray.hasValue(R.styleable.background_bl_unFocused_gradient_endColor)) &&
                        android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)) {
            int[] colors;
            if (typedArray.hasValue(R.styleable.background_bl_gradient_centerColor)) {
                colors = new int[3];
                colors[0] = startColor;
                colors[1] = centerColor;
                colors[2] = endColor;
            } else {
                colors = new int[2];
                colors[0] = startColor;
                colors[1] = endColor;
            }
            drawable.setColors(colors);
        }
        if (gradientType == LINEAR_GRADIENT &&
                android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN &&
                (typedArray.hasValue(R.styleable.background_bl_gradient_angle) ||
                        typedArray.hasValue(R.styleable.background_bl_checkable_gradient_angle) ||
                        typedArray.hasValue(R.styleable.background_bl_checked_gradient_angle) ||
                        typedArray.hasValue(R.styleable.background_bl_enabled_gradient_angle) ||
                        typedArray.hasValue(R.styleable.background_bl_selected_gradient_angle) ||
                        typedArray.hasValue(R.styleable.background_bl_pressed_gradient_angle) ||
                        typedArray.hasValue(R.styleable.background_bl_focused_gradient_angle) ||
                        typedArray.hasValue(R.styleable.background_bl_unCheckable_gradient_angle) ||
                        typedArray.hasValue(R.styleable.background_bl_unChecked_gradient_angle) ||
                        typedArray.hasValue(R.styleable.background_bl_unEnabled_gradient_angle) ||
                        typedArray.hasValue(R.styleable.background_bl_unSelected_gradient_angle) ||
                        typedArray.hasValue(R.styleable.background_bl_unPressed_gradient_angle) ||
                        typedArray.hasValue(R.styleable.background_bl_unFocused_gradient_angle))) {
            gradientAngle %= 360;
            if (gradientAngle % 45 != 0) {
                throw new XmlPullParserException(typedArray.getPositionDescription()
                        + "<gradient> tag requires 'angle' attribute to "
                        + "be a multiple of 45");
            }
            GradientDrawable.Orientation mOrientation = GradientDrawable.Orientation.LEFT_RIGHT;
            switch (gradientAngle) {
                case 0:
                    mOrientation = GradientDrawable.Orientation.LEFT_RIGHT;
                    break;
                case 45:
                    mOrientation = GradientDrawable.Orientation.BL_TR;
                    break;
                case 90:
                    mOrientation = GradientDrawable.Orientation.BOTTOM_TOP;
                    break;
                case 135:
                    mOrientation = GradientDrawable.Orientation.BR_TL;
                    break;
                case 180:
                    mOrientation = GradientDrawable.Orientation.RIGHT_LEFT;
                    break;
                case 225:
                    mOrientation = GradientDrawable.Orientation.TR_BL;
                    break;
                case 270:
                    mOrientation = GradientDrawable.Orientation.TOP_BOTTOM;
                    break;
                case 315:
                    mOrientation = GradientDrawable.Orientation.TL_BR;
                    break;
            }
            drawable.setOrientation(mOrientation);

        }

        if (typedArray.hasValue(R.styleable.background_bl_padding_left) &&
                typedArray.hasValue(R.styleable.background_bl_padding_top) &&
                typedArray.hasValue(R.styleable.background_bl_padding_right) &&
                typedArray.hasValue(R.styleable.background_bl_padding_bottom)) {
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                drawable.setPadding(padding.left, padding.top, padding.right, padding.bottom);
            } else {
                try {
                    Field paddingField = drawable.getClass().getDeclaredField("mPadding");
                    paddingField.setAccessible(true);
                    paddingField.set(drawable, padding);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return drawable;
    }

    /**
     * 生成渐变描边。{@link GradientDrawable} 的 stroke 仅支持纯色，这里用 LayerDrawable
     * 模拟：底层为渐变描边层，顶层为填充层（向内缩进 strokeWidth，露出底层形成渐变描边）。
     */
    public Drawable createStrokeGradientDrawable() throws XmlPullParserException {
        GradientDrawable fillDrawable = create();
        GradientDrawable strokeLayer = buildStrokeGradientLayer();
        int width = (int) typedArray.getDimension(R.styleable.background_bl_stroke_width, 0f);
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{strokeLayer, fillDrawable});
        layerDrawable.setLayerInset(1, width, width, width, width);
        return layerDrawable;
    }

    private GradientDrawable buildStrokeGradientLayer() {
        GradientDrawable layer = new GradientDrawable();
        if (typedArray.hasValue(R.styleable.background_bl_shape)) {
            layer.setShape(typedArray.getInt(R.styleable.background_bl_shape, 0));
        }
        if (typedArray.hasValue(R.styleable.background_bl_corners_radius)) {
            layer.setCornerRadius(typedArray.getDimension(R.styleable.background_bl_corners_radius, 0));
        }
        float[] radius = new float[8];
        int tlIdx = rtl ? 2 : 0;
        int trIdx = rtl ? 0 : 2;
        int brIdx = rtl ? 6 : 4;
        int blIdx = rtl ? 4 : 6;
        if (typedArray.hasValue(R.styleable.background_bl_corners_bottomLeftRadius)) {
            radius[blIdx] = typedArray.getDimension(R.styleable.background_bl_corners_bottomLeftRadius, 0);
            radius[blIdx + 1] = radius[blIdx];
        }
        if (typedArray.hasValue(R.styleable.background_bl_corners_bottomRightRadius)) {
            radius[brIdx] = typedArray.getDimension(R.styleable.background_bl_corners_bottomRightRadius, 0);
            radius[brIdx + 1] = radius[brIdx];
        }
        if (typedArray.hasValue(R.styleable.background_bl_corners_topLeftRadius)) {
            radius[tlIdx] = typedArray.getDimension(R.styleable.background_bl_corners_topLeftRadius, 0);
            radius[tlIdx + 1] = radius[tlIdx];
        }
        if (typedArray.hasValue(R.styleable.background_bl_corners_topRightRadius)) {
            radius[trIdx] = typedArray.getDimension(R.styleable.background_bl_corners_topRightRadius, 0);
            radius[trIdx + 1] = radius[trIdx];
        }
        if (typedArray.hasValue(R.styleable.background_bl_corners_leftRadius)) {
            float lr = typedArray.getDimension(R.styleable.background_bl_corners_leftRadius, 0);
            radius[tlIdx] = lr;
            radius[blIdx] = lr;
            radius[tlIdx + 1] = lr;
            radius[blIdx + 1] = lr;
        }
        if (typedArray.hasValue(R.styleable.background_bl_corners_topRadius)) {
            radius[0] = radius[1] = radius[2] = radius[3] =
                    typedArray.getDimension(R.styleable.background_bl_corners_topRadius, 0);
        }
        if (typedArray.hasValue(R.styleable.background_bl_corners_rightRadius)) {
            float rr = typedArray.getDimension(R.styleable.background_bl_corners_rightRadius, 0);
            radius[trIdx] = rr;
            radius[brIdx] = rr;
            radius[trIdx + 1] = rr;
            radius[brIdx + 1] = rr;
        }
        if (typedArray.hasValue(R.styleable.background_bl_corners_bottomRadius)) {
            radius[4] = radius[5] = radius[6] = radius[7] =
                    typedArray.getDimension(R.styleable.background_bl_corners_bottomRadius, 0);
        }
        if (hasSetRadius(radius)) {
            layer.setCornerRadii(radius);
        }

        int startColor = typedArray.getColor(R.styleable.background_bl_stroke_gradient_startColor, 0);
        int endColor = typedArray.getColor(R.styleable.background_bl_stroke_gradient_endColor, 0);
        if (typedArray.hasValue(R.styleable.background_bl_stroke_gradient_centerColor)) {
            layer.setColors(new int[]{startColor,
                    typedArray.getColor(R.styleable.background_bl_stroke_gradient_centerColor, 0), endColor});
        } else {
            layer.setColors(new int[]{startColor, endColor});
        }
        layer.setOrientation(orientationFor(typedArray.getInt(R.styleable.background_bl_stroke_gradient_angle, 0)));
        return layer;
    }

    private static GradientDrawable.Orientation orientationFor(int angle) {
        angle %= 360;
        switch (angle) {
            case 45:
                return GradientDrawable.Orientation.BL_TR;
            case 90:
                return GradientDrawable.Orientation.BOTTOM_TOP;
            case 135:
                return GradientDrawable.Orientation.BR_TL;
            case 180:
                return GradientDrawable.Orientation.RIGHT_LEFT;
            case 225:
                return GradientDrawable.Orientation.TR_BL;
            case 270:
                return GradientDrawable.Orientation.TOP_BOTTOM;
            case 315:
                return GradientDrawable.Orientation.TL_BR;
            default:
                return GradientDrawable.Orientation.LEFT_RIGHT;
        }
    }

    private boolean hasSetRadius(float[] radius) {
        boolean hasSet = false;
        for (float f : radius) {
            if (f != 0.0f) {
                hasSet = true;
                break;
            }
        }
        return hasSet;
    }
}
