package com.noober.background.drawable;

import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

import androidx.annotation.AttrRes;

import com.noober.background.R;

public class GradientStateDrawableCreator implements ICreateDrawable {

    private TypedArray typedArray;

    @SuppressWarnings("WeakerAccess")
    public GradientStateDrawableCreator(TypedArray typedArray) {
        this.typedArray = typedArray;
    }

    @Override
    public StateListDrawable create() throws Exception {
        StateListDrawable stateListDrawable = new StateListDrawable();

        if(typedArray.hasValue(R.styleable.background_bl_checkable_gradient_startColor)
                && typedArray.hasValue(R.styleable.background_bl_unCheckable_gradient_startColor)){
            setSelectorDrawable(stateListDrawable, android.R.attr.state_checkable);
            setSelectorDrawable(stateListDrawable, -android.R.attr.state_checkable);
        }
        if(typedArray.hasValue(R.styleable.background_bl_checked_gradient_startColor) &&
                typedArray.hasValue(R.styleable.background_bl_unChecked_gradient_startColor)){
            setSelectorDrawable(stateListDrawable, android.R.attr.state_checked);
            setSelectorDrawable(stateListDrawable, -android.R.attr.state_checked);
        }
        if(typedArray.hasValue(R.styleable.background_bl_enabled_gradient_startColor) &&
                typedArray.hasValue(R.styleable.background_bl_unEnabled_gradient_startColor)){
            setSelectorDrawable(stateListDrawable, android.R.attr.state_enabled);
            setSelectorDrawable(stateListDrawable, -android.R.attr.state_enabled);
        }
        if(typedArray.hasValue(R.styleable.background_bl_selected_gradient_startColor) &&
                typedArray.hasValue(R.styleable.background_bl_unSelected_gradient_startColor)){
            setSelectorDrawable(stateListDrawable, android.R.attr.state_selected);
            setSelectorDrawable(stateListDrawable, -android.R.attr.state_selected);
        }
        if(typedArray.hasValue(R.styleable.background_bl_pressed_gradient_startColor) &&
                typedArray.hasValue(R.styleable.background_bl_unPressed_gradient_startColor)){
            setSelectorDrawable(stateListDrawable, android.R.attr.state_pressed);
            setSelectorDrawable(stateListDrawable, -android.R.attr.state_pressed);
        }
        if(typedArray.hasValue(R.styleable.background_bl_focused_gradient_startColor) &&
                typedArray.hasValue(R.styleable.background_bl_unFocused_gradient_startColor)){
            setSelectorDrawable(stateListDrawable, android.R.attr.state_focused);
            setSelectorDrawable(stateListDrawable, -android.R.attr.state_focused);
        }

        return stateListDrawable;
    }

    private void setSelectorDrawable(StateListDrawable stateListDrawable, @AttrRes int functionId) throws Exception {
        GradientDrawable tmpDrawable = DrawableFactory.getDrawable(typedArray, functionId);
        stateListDrawable.addState(new int[]{functionId}, tmpDrawable);
    }

}
