package com.noober.background.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatMultiAutoCompleteTextView;

import com.noober.background.BackgroundFactory;

/**
 * @author: xTao
 * @date: 2026/1/9 16:53
 */
public class BLMultiAutoCompleteTextView extends AppCompatMultiAutoCompleteTextView {
    public BLMultiAutoCompleteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        BackgroundFactory.setViewBackground(context, attrs, this);
    }
}
