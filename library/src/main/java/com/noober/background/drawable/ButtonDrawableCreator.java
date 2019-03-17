package com.noober.background.drawable;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.AttrRes;

import com.noober.background.R;

public class ButtonDrawableCreator implements ICreateDrawable {

    private TypedArray typedArray;
    private TypedArray buttonTa;

    public ButtonDrawableCreator(TypedArray typedArray, TypedArray buttonTa) {
        this.typedArray = typedArray;
        this.buttonTa = buttonTa;
    }

    @Override
    public Drawable create() throws Exception {
        StateListDrawable stateListDrawable = new StateListDrawable();

        for (int i = 0; i < buttonTa.getIndexCount(); i++) {
            int attr = buttonTa.getIndex(i);

            if (attr == R.styleable.background_button_drawable_bl_checked_button_drawable) {
                setSelectorDrawable(typedArray, buttonTa, stateListDrawable, attr, android.R.attr.state_checked);
            } else if (attr == R.styleable.background_button_drawable_bl_unChecked_button_drawable) {
                setSelectorDrawable(typedArray, buttonTa, stateListDrawable, attr, -android.R.attr.state_checked);
            }
        }
        return stateListDrawable;
    }

    private void setSelectorDrawable(TypedArray typedArray, TypedArray buttonTa,
                                     StateListDrawable stateListDrawable, int attr, @AttrRes int functionId) throws Exception {
        int color = 0;
        Drawable resDrawable = null;

        //这里用try catch先判断是否是颜色而不是直接调用getDrawable，为了方便填入的是颜色时可以沿用其他属性,
        //否则如果是其他资源会覆盖app:corners_radius等其他shape属性设置的效果
        try {
            color = buttonTa.getColor(attr, 0);
            if (color == 0) {
                resDrawable = buttonTa.getDrawable(attr);
            }
        } catch (Exception e) {
            resDrawable = buttonTa.getDrawable(attr);
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
