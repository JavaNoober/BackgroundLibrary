package com.noober.background.drawable;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

import com.noober.background.R;
import com.noober.background.common.MultiSelector;
import com.noober.background.common.ResourceUtils;

public class MultiTextColorSelectorColorCreator implements ICreateColorState {

    private TypedArray selectorTa;
    private Context context;
    private int[][] states = new int[][]{};
    private int[] colors = new int[]{};
    private int index;
    public MultiTextColorSelectorColorCreator(Context context, TypedArray selectorTa) {
        this.selectorTa = selectorTa;
        this.context = context;
    }

    @Override
    public ColorStateList create() {
        states = new int[selectorTa.getIndexCount()][];
        colors = new int[selectorTa.getIndexCount()];

        for (int i = 0; i < selectorTa.getIndexCount(); i++) {
            int attr = selectorTa.getIndex(i);

            if (attr == R.styleable.background_multi_selector_text_bl_multi_text_selector1) {
                addState(attr);
            } else if (attr == R.styleable.background_multi_selector_text_bl_multi_text_selector2) {
                addState(attr);
            } else if (attr == R.styleable.background_multi_selector_text_bl_multi_text_selector3) {
                addState(attr);
            } else if (attr == R.styleable.background_multi_selector_text_bl_multi_text_selector4) {
                addState(attr);
            } else if (attr == R.styleable.background_multi_selector_text_bl_multi_text_selector5) {
                addState(attr);
            } else if (attr == R.styleable.background_multi_selector_text_bl_multi_text_selector6) {
                addState(attr);
            }
        }
        return new ColorStateList(states, colors);
    }

    private void addState(int attr) {
        String value = selectorTa.getString(attr);
        if(value != null){
            String[] vArray = value.split(",");
            if(vArray.length < 2){
                throw new IllegalArgumentException("Attributes and drawable must be set at the same time");
            }
            int color = 0;
            int[] attrId = new int[vArray.length - 1];
            for (int p = 0; p < vArray.length; p++){
                String attrStr = vArray[p];
                //取出资源id
                if(p == vArray.length - 1){
                    color = ResourceUtils.getColor(context, attrStr);
                    if(color == -1){
                        throw new IllegalArgumentException("cannot find color from the last attribute");
                    }
                }else {
                    MultiSelector multiSelector = MultiSelector.getMultiAttr(attrStr.replace("-", ""));
                    if(multiSelector == null){
                        throw new IllegalArgumentException("the attribute of bl_multi_selector only support " +
                                "state_checkable, state_checked, state_enabled, state_selected, state_pressed, state_focused, " +
                                "state_hovered, state_activated");
                    }
                    if(attrStr.contains("-")){
                        attrId[p] = -multiSelector.id;
                    }else {
                        attrId[p] = multiSelector.id;
                    }
                }
            }
            states[index] = attrId;
            colors[index] = color;
            index++;
        }
    }
}
