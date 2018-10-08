package com.noober.background.drawable;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

import com.noober.background.R;
import com.noober.background.utils.TypeValueHelper;

public class PressDrawableCreator implements ICreateDrawable {

    private GradientDrawable drawable;
    private TypedArray pressTa;
    private TypedArray typedArray;

    PressDrawableCreator(GradientDrawable drawable, TypedArray typedArray, TypedArray pressTa) {
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
}
