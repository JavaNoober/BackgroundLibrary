package com.noober.background.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.SparseIntArray;

import com.noober.background.R;

import java.util.Arrays;
import java.util.List;

public class TypeValueHelper {

    private static int[] attrs = {R.styleable.background_shape, R.styleable.background_solid_color,
            R.styleable.background_corners_radius, R.styleable.background_corners_bottomLeftRadius, R.styleable.background_corners_bottomRightRadius,
            R.styleable.background_corners_topLeftRadius, R.styleable.background_corners_topRightRadius,
            R.styleable.background_gradient_angle, R.styleable.background_gradient_centerX, R.styleable.background_gradient_centerY, R.styleable.background_gradient_centerColor,
            R.styleable.background_gradient_endColor, R.styleable.background_gradient_startColor, R.styleable.background_gradient_gradientRadius, R.styleable.background_gradient_type,
            R.styleable.background_gradient_useLevel,
            R.styleable.background_padding_left, R.styleable.background_padding_top, R.styleable.background_padding_right, R.styleable.background_padding_bottom,
            R.styleable.background_size_width, R.styleable.background_size_height,
            R.styleable.background_stroke_width, R.styleable.background_stroke_color, R.styleable.background_stroke_dashWidth, R.styleable.background_stroke_dashGap,
    };
    public static final SparseIntArray sAppearanceValues = new SparseIntArray();

    static {
        sAppearanceValues.put(R.styleable.background_shape, R.styleable.background_shape);
        sAppearanceValues.put(R.styleable.background_solid_color, R.styleable.background_solid_color);
        sAppearanceValues.put(R.styleable.background_corners_radius, R.styleable.background_corners_radius);
        sAppearanceValues.put(R.styleable.background_corners_bottomLeftRadius, R.styleable.background_corners_bottomLeftRadius);
        sAppearanceValues.put(R.styleable.background_corners_bottomRightRadius, R.styleable.background_corners_bottomRightRadius);
        sAppearanceValues.put(R.styleable.background_corners_topLeftRadius, R.styleable.background_corners_topLeftRadius);
        sAppearanceValues.put(R.styleable.background_corners_topRightRadius, R.styleable.background_corners_topRightRadius);
        sAppearanceValues.put(R.styleable.background_gradient_angle, R.styleable.background_gradient_angle);
        sAppearanceValues.put(R.styleable.background_gradient_centerX, R.styleable.background_gradient_centerX);
        sAppearanceValues.put(R.styleable.background_gradient_centerY, R.styleable.background_gradient_centerY);
        sAppearanceValues.put(R.styleable.background_gradient_centerColor, R.styleable.background_gradient_centerColor);
        sAppearanceValues.put(R.styleable.background_gradient_endColor, R.styleable.background_gradient_endColor);
        sAppearanceValues.put(R.styleable.background_gradient_startColor, R.styleable.background_gradient_startColor);
        sAppearanceValues.put(R.styleable.background_gradient_gradientRadius, R.styleable.background_gradient_gradientRadius);
        sAppearanceValues.put(R.styleable.background_gradient_type, R.styleable.background_gradient_type);
        sAppearanceValues.put(R.styleable.background_gradient_useLevel, R.styleable.background_gradient_useLevel);
        sAppearanceValues.put(R.styleable.background_padding_left, R.styleable.background_padding_left);
        sAppearanceValues.put(R.styleable.background_padding_top, R.styleable.background_padding_top);
        sAppearanceValues.put(R.styleable.background_padding_right, R.styleable.background_padding_right);
        sAppearanceValues.put(R.styleable.background_padding_bottom, R.styleable.background_padding_bottom);
        sAppearanceValues.put(R.styleable.background_size_width, R.styleable.background_size_width);
        sAppearanceValues.put(R.styleable.background_size_height, R.styleable.background_size_height);
        sAppearanceValues.put(R.styleable.background_stroke_width, R.styleable.background_stroke_width);
        sAppearanceValues.put(R.styleable.background_stroke_color, R.styleable.background_stroke_color);
        sAppearanceValues.put(R.styleable.background_stroke_dashWidth, R.styleable.background_stroke_dashWidth);
        sAppearanceValues.put(R.styleable.background_stroke_dashGap, R.styleable.background_stroke_dashGap);
    }

    public static TypedArray getTypedArray(Context context, AttributeSet attributeSet){
        return context.getApplicationContext().obtainStyledAttributes(attributeSet, attrs);
    }

    public static int getColor(TypedArray ta, int colorIndex){
        String colorStr;
        int color = ta.getColor(colorIndex, -1);
        if(color == -1){
            colorStr = ta.getString(colorIndex);
            if(colorStr == null){
                return -1;
            }
        }else {
            colorStr = Integer.toHexString(color);
        }
        if(!colorStr.startsWith("#")){
            colorStr = String.format("#%s", colorStr);
        }
        return Color.parseColor(colorStr);
    }
    public void getDrawable(){

    }

}
