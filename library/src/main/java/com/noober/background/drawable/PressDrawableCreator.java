package com.noober.background.drawable;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.AttrRes;

import com.noober.background.R;
import com.noober.background.utils.TypeValueHelper;

public class PressDrawableCreator implements ICreateDrawable {

    private GradientDrawable drawable;
    private TypedArray pressTa;
    private TypedArray typedArray;

    public PressDrawableCreator(GradientDrawable drawable, TypedArray typedArray, TypedArray pressTa) {
        this.drawable = drawable;
        this.pressTa = pressTa;
        this.typedArray = typedArray;
    }

    @Override
    public Drawable create() throws Exception{
        StateListDrawable stateListDrawable = new StateListDrawable();
        for (int i = 0; i < pressTa.getIndexCount(); i++) {
            int attr = TypeValueHelper.sAppearancePressValues.get(pressTa.getIndex(i), -1);
            if (attr == -1) {
                continue;
            }
            int typeIndex = pressTa.getIndex(i);

            if (attr == R.styleable.background_press_pressed_color) {
                int color = pressTa.getColor(typeIndex, 0);
                GradientDrawable pressDrawable = DrawableFactory.getDrawable(typedArray);
                pressDrawable.setColor(color);
                stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, pressDrawable);
            } else if (attr == R.styleable.background_press_unpressed_color) {
                int color = pressTa.getColor(typeIndex, 0);
                drawable.setColor(color);
                stateListDrawable.addState(new int[]{-android.R.attr.state_pressed}, drawable);
            }
        }
        return stateListDrawable;
    }

    private void setSelectorDrawable(TypedArray typedArray, TypedArray selectorTa,
                                     StateListDrawable stateListDrawable, int typeIndex, @AttrRes int functionId) throws Exception {
        int color = 0;
        Drawable resDrawable = null;

        //这里用try catch先判断是否是颜色而不是直接调用getDrawable，为了方便填入的是颜色时可以沿用其他属性,
        //否则如果是其他资源会覆盖app:corners_radius等其他shape属性设置的效果
        try {
            color = selectorTa.getColor(typeIndex, 0);
            if (color == 0) {
                resDrawable = selectorTa.getDrawable(typeIndex);
            }
        } catch (Exception e) {
            resDrawable = selectorTa.getDrawable(typeIndex);
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
