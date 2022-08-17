package com.noober.background.drawable;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Author: xiaoqi
 * Date: 2022/8/17 3:13 下午
 * Description:
 */
public interface ITextViewOperator {
    void invoke(Context context, AttributeSet attrs, TextView textView);
}
