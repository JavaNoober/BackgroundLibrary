package com.noober.background.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.noober.background.BackgroundFactory;

public class BLView extends View {
    public BLView(Context context) {
        super(context);
    }

    public BLView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BLView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        BackgroundFactory.setViewBackground(context, attrs, this);
    }
}
