package com.noober.background.drawable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

import com.noober.background.R;
import com.noober.background.common.MultiSelector;
import com.noober.background.common.ResourceUtils;

public class MultiSelectorDrawableCreator implements ICreateDrawable {

    private TypedArray selectorTa;
    private Context context;

    public MultiSelectorDrawableCreator(Context context, TypedArray selectorTa) {
        this.selectorTa = selectorTa;
        this.context = context;
    }

    @Override
    public Drawable create() {
        StateListDrawable stateListDrawable = new StateListDrawable();

        for (int i = 0; i < selectorTa.getIndexCount(); i++) {
            int attr = selectorTa.getIndex(i);

            if (attr == R.styleable.background_multi_selector_bl_multi_selector1) {
                addState(stateListDrawable, attr);
            } else if (attr == R.styleable.background_multi_selector_bl_multi_selector2) {
                addState(stateListDrawable, attr);
            } else if (attr == R.styleable.background_multi_selector_bl_multi_selector3) {
                addState(stateListDrawable, attr);
            } else if (attr == R.styleable.background_multi_selector_bl_multi_selector4) {
                addState(stateListDrawable, attr);
            } else if (attr == R.styleable.background_multi_selector_bl_multi_selector5) {
                addState(stateListDrawable, attr);
            } else if (attr == R.styleable.background_multi_selector_bl_multi_selector6) {
                addState(stateListDrawable, attr);
            }
        }
        return stateListDrawable;
    }

    private void addState(StateListDrawable stateListDrawable, int attr) {
        String value = selectorTa.getString(attr);
        if(value != null){
            String[] vArray = value.split(",");
            if(vArray.length < 2){
                throw new IllegalArgumentException("Attributes and drawable must be set at the same time");
            }
            Drawable drawable = null;
            int[] attrId = new int[vArray.length];
            for (int p = 0; p < vArray.length; p++){
                String attrStr = vArray[p];
                //取出资源id
                if(p == vArray.length - 1){
                    drawable = ResourceUtils.getDrawable(context, attrStr);
                    if(drawable == null){
                        throw new IllegalArgumentException("cannot find drawable from the last attribute");
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
            stateListDrawable.addState(attrId, drawable);
        }
    }
}
