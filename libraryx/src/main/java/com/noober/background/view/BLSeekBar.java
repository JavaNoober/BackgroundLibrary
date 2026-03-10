package com.noober.background.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatSeekBar;

import com.noober.background.BackgroundFactory;

/**
 * @author: xTao
 * @date: 2026/1/9 16:54
 */
public class BLSeekBar extends AppCompatSeekBar {
    public BLSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        BackgroundFactory.setViewBackground(context, attrs, this);
    }
}
