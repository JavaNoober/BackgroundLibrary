package com.noober.background.view;

import android.content.Context;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.util.AttributeSet;

import com.noober.background.BackgroundFactory;

/**
 * @author: xTao
 * @date: 2026/1/9 18:01
 */
public class BLAutoCompleteTextView extends AppCompatAutoCompleteTextView {
    public BLAutoCompleteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        BackgroundFactory.setViewBackground(context, attrs, this);
    }
}
