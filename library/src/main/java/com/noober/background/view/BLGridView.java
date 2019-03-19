package com.noober.background.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

import com.noober.background.BackgroundFactory;

public class BLGridView extends GridView {
    public BLGridView(Context context) {
        super(context);
    }

    public BLGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BLGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        BackgroundFactory.setViewBackground(context, attrs, this);
    }
}
