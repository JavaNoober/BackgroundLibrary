package com.noober.background.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatCheckedTextView;

import com.noober.background.BackgroundFactory;

/**
 * @author: xTao
 * @date: 2026/1/9 16:49
 */
public class BLCheckedTextView extends AppCompatCheckedTextView {
    public BLCheckedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        BackgroundFactory.setViewBackground(context, attrs, this);
    }
}
