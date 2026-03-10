package com.noober.background.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatSpinner;

import com.noober.background.BackgroundFactory;

/**
 * @author: xTao
 * @date: 2026/1/9 16:47
 */
public class BLSpinner extends AppCompatSpinner {
    public BLSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        BackgroundFactory.setViewBackground(context, attrs, this);
    }
}
