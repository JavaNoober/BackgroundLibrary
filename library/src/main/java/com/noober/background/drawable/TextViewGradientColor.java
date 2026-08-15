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
    private int endColor = 0;
    private int startColor = 0;
    private int orientation = 0;

    @Override
    public void invoke(Context context, AttributeSet attrs, final TextView textView) {
        TypedArray textTa = context.obtainStyledAttributes(attrs, R.styleable.bl_text);
        try {
            if(textTa.getIndexCount() == 0){
                return;
            }
            boolean hasEndColor = false;
            boolean hasStartColor = false;
            for (int i = 0; i < textTa.getIndexCount(); i++) {
                int attr = textTa.getIndex(i);
                if(attr == R.styleable.bl_text_bl_text_gradient_endColor){
                    endColor = textTa.getColor(attr, 0);
                    hasEndColor = true;
                }else if(attr == R.styleable.bl_text_bl_text_gradient_startColor){
                    startColor = textTa.getColor(attr, 0);
                    hasStartColor = true;
                }else if(attr == R.styleable.bl_text_bl_text_gradient_orientation){
                    orientation = textTa.getInt(attr, 0);
                }
            }

            if(hasStartColor && hasEndColor){
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
                            CharSequence text = textView.getText();
                            float width = (text == null || text.length() == 0) ? textView.getWidth() : textView.getPaint().measureText(text.toString());
                            textView.getPaint().setShader(new LinearGradient(0, 0f, width, 0f, startColor, endColor, Shader.TileMode.CLAMP));
                            textView.invalidate();
                        }
                    });
                }
            }else if(hasStartColor){
                textView.setTextColor(startColor);
            }else if(hasEndColor){
                textView.setTextColor(endColor);
            }
        }catch (Exception e){

        }finally {
            textTa.recycle();
        }
    }
}
