package com.noober.background.drawable;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Author: xiaoqi
 * Date: 2022/8/17 3:11 下午
 * Description:
 */
public class TextViewFactory {

    public static void setTextGradientColor(Context context, AttributeSet attrs, final TextView textView){
       new TextViewGradientColor().invoke(context, attrs, textView);
    }
}
