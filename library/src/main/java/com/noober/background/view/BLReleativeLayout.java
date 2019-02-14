package com.noober.background.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.noober.background.BackgroundFactory;

public class BLReleativeLayout extends RelativeLayout {
    public BLReleativeLayout(Context context) {
        super(context);
    }

    public BLReleativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BLReleativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        BackgroundFactory.setViewBackground(context, attrs, this);
    }
}
