package com.noober.background.drawable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

import com.noober.background.R;

/**
 * Author: xiaoqi
 * Date: 2022/8/17 3:14 下午
 * Description:
 */
public class TextViewGradientColor implements ITextViewOperator{
    private int endColor = -1;
    private int startColor = -1;
    private int orientation = 0;

    @Override
    public void invoke(Context context, AttributeSet attrs, final TextView textView) {
        TypedArray textTa = context.obtainStyledAttributes(attrs, R.styleable.bl_text);
        try {
            if(textTa.getIndexCount() == 0){
                return;
            }
            for (int i = 0; i < textTa.getIndexCount(); i++) {
                int attr = textTa.getIndex(i);
                if(attr == R.styleable.bl_text_bl_text_gradient_endColor){
                    endColor = textTa.getColor(attr, -1);
                }else if(attr == R.styleable.bl_text_bl_text_gradient_startColor){
                    startColor = textTa.getColor(attr, -1);
                }else if(attr == R.styleable.bl_text_bl_text_gradient_orientation){
                    orientation = textTa.getInt(attr, 0);
                }
            }

            if(endColor == -1 && startColor != -1){
                textView.setTextColor(startColor);
            }else if(startColor == -1 && endColor != -1){
                textView.setTextColor(endColor);
            }else if(endColor != -1 && startColor != -1){
                if(orientation == 0){
                    textView.post(new Runnable() {
                        @Override
                        public void run() {
                            textView.getPaint().setShader(new LinearGradient(0f, 0f, 0f, textView.getPaint().descent() - textView.getPaint().ascent(),
                                startColor, endColor, Shader.TileMode.REPEAT));
                            textView.invalidate();
                        }
                    });

                }else {
                    textView.post(new Runnable() {
                        @Override
                        public void run() {
                            textView.getPaint().setShader(new LinearGradient(0, 0f, textView.getMeasuredWidth(), 0f, startColor, endColor, Shader.TileMode.REPEAT));
                            textView.invalidate();
                        }
                    });
                }
            }
        }catch (Exception e){

        }finally {
            textTa.recycle();
        }
    }
}
