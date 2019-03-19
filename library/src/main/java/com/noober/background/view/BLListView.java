package com.noober.background.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import com.noober.background.BackgroundFactory;

public class BLListView extends ListView {
    public BLListView(Context context) {
        super(context);
    }

    public BLListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BLListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        BackgroundFactory.setViewBackground(context, attrs, this);
    }
}
