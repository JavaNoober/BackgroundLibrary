package com.noober.background.drawable;

import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

import com.noober.background.R;

public class GradientStateDrawableCreator implements ICreateDrawable {

    private TypedArray typedArray;
    private boolean rtl;

    @SuppressWarnings("WeakerAccess")
    public GradientStateDrawableCreator(TypedArray typedArray) {
        this(typedArray, false);
    }

    @SuppressWarnings("WeakerAccess")
    public GradientStateDrawableCreator(TypedArray typedArray, boolean rtl) {
        this.typedArray = typedArray;
        this.rtl = rtl;
    }

    @Override
    public StateListDrawable create() throws Exception {
        StateListDrawable stateListDrawable = new StateListDrawable();

        boolean enabledSet = typedArray.hasValue(R.styleable.background_bl_enabled_gradient_startColor)
                || typedArray.hasValue(R.styleable.background_bl_unEnabled_gradient_startColor);
        boolean enabledPos = typedArray.hasValue(R.styleable.background_bl_enabled_gradient_startColor);

        // 禁用态一票覆盖（仅当 enabled 维度被设置时）
        if (enabledSet && typedArray.hasValue(R.styleable.background_bl_unEnabled_gradient_startColor)) {
            setSelectorDrawable(stateListDrawable, new int[]{-android.R.attr.state_enabled}, -android.R.attr.state_enabled);
        }

        addDimension(stateListDrawable, R.styleable.background_bl_checkable_gradient_startColor,
                R.styleable.background_bl_unCheckable_gradient_startColor, android.R.attr.state_checkable, enabledPos);
        addDimension(stateListDrawable, R.styleable.background_bl_checked_gradient_startColor,
                R.styleable.background_bl_unChecked_gradient_startColor, android.R.attr.state_checked, enabledPos);
        addDimension(stateListDrawable, R.styleable.background_bl_selected_gradient_startColor,
                R.styleable.background_bl_unSelected_gradient_startColor, android.R.attr.state_selected, enabledPos);
        addDimension(stateListDrawable, R.styleable.background_bl_pressed_gradient_startColor,
                R.styleable.background_bl_unPressed_gradient_startColor, android.R.attr.state_pressed, enabledPos);
        addDimension(stateListDrawable, R.styleable.background_bl_focused_gradient_startColor,
                R.styleable.background_bl_unFocused_gradient_startColor, android.R.attr.state_focused, enabledPos);

        // enabled 正渐变兜底
        if (enabledSet && enabledPos) {
            setSelectorDrawable(stateListDrawable, new int[]{android.R.attr.state_enabled}, android.R.attr.state_enabled);
        }

        return stateListDrawable;
    }

    private void addDimension(StateListDrawable stateListDrawable, int posAttr, int negAttr,
                              int posState, boolean enabledPos) throws Exception {
        if (typedArray.hasValue(posAttr)) {
            setSelectorDrawable(stateListDrawable, buildState(posState, enabledPos), posState);
        }
        if (typedArray.hasValue(negAttr)) {
            setSelectorDrawable(stateListDrawable, buildState(-posState, enabledPos), -posState);
        }
    }

    private int[] buildState(int stateFlag, boolean enabledPos) {
        return enabledPos ? new int[]{stateFlag, android.R.attr.state_enabled} : new int[]{stateFlag};
    }

    private void setSelectorDrawable(StateListDrawable stateListDrawable, int[] state, int functionId) throws Exception {
        GradientDrawable tmpDrawable = DrawableFactory.getDrawable(typedArray, functionId, rtl);
        stateListDrawable.addState(state, tmpDrawable);
    }
}