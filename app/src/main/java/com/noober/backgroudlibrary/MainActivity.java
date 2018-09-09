package com.noober.backgroudlibrary;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.noober.background.BackgroundFactory;
import com.noober.background.config.BackgroundConfig;
import com.noober.background.config.ShapeConfig;
import com.noober.background.utils.TypeValueHelper;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater.from(this).setFactory(new BackgroundFactory());

//        LayoutInflater.from(this).setFactory(new LayoutInflater.Factory() {
//            @Override
//            public View onCreateView(String name, Context context, AttributeSet attrs) {
//                Log.e("MainActivity", "name :" + name);
//                int count = attrs.getAttributeCount();
//                for (int i = 0; i < count; i++) {
//                    Log.e("MainActivity", "AttributeName :" + attrs.getAttributeName(i) + "AttributeValue :"+ attrs.getAttributeValue(i));
//                }
//
//                int shapeValue = attrs.getAttributeIntValue(BackgroundConfig.NAMESPACE, BackgroundConfig.SHAPE, 0);
//                ShapeConfig shapeConfig = ShapeConfig.get(shapeValue);
//                GradientDrawable shapeDrawable = null;
//                if(shapeConfig == ShapeConfig.Oval){
////                    shapeDrawable = new ShapeDrawable(new OvalShape());
//                }else if(shapeConfig == ShapeConfig.Line){
////                    shapeDrawable = new ShapeDrawable(new LineS());
//                }else if(shapeConfig == ShapeConfig.Ring){
////                    shapeDrawable = new ShapeDrawable(new RingSh());
//                }else {
//                    shapeDrawable = new GradientDrawable();
//                }
//                if(name.equals("TextView")){
//                    int solidColor = attrs.getAttributeResourceValue(BackgroundConfig.NAMESPACE, BackgroundConfig.SOLID_COLOR, -1);
//                    int strokeColor = attrs.getAttributeResourceValue(BackgroundConfig.NAMESPACE, BackgroundConfig.STROKE_COLOR, -1);
//                    String strokeWidth = attrs.getAttributeValue(BackgroundConfig.NAMESPACE, BackgroundConfig.STROKE_WIDTH);
//                    Log.e("MainActivity", "shapeValue :" + shapeValue);
//                    Log.e("MainActivity", "solidColor :" + solidColor);
//                    Log.e("MainActivity", "strokeColor :" + strokeColor);
//                    Log.e("MainActivity", "strokeWidth :" + strokeWidth);
////                shapeDrawable.setColor(solidColor);
//                    int[] mAttr = {R.attr.enable_background, R.attr.solid_color,  R.attr.stroke_color, R.attr.stroke_width};
//                    TypedArray ta = context.obtainStyledAttributes(attrs, mAttr);
//
////                    if(!ta.getBoolean(0, false)){
////                        return null;
////                    }
//
//                    Log.e("MainActivity", "getIndexCount :" + ta.getIndexCount());
//                    Log.e("MainActivity", "solidColor :" + TypeValueHelper.getColor(ta, 1));
//                    Log.e("MainActivity", "strokeColor :" + TypeValueHelper.getColor(ta, 2));
//                    Log.e("MainActivity", "strokeWidth :" + ta.getDimension(3, -1));
//                    shapeDrawable.setStroke((int) ta.getDimension(3, -1), TypeValueHelper.getColor(ta, 2));
//                    shapeDrawable.setColor(TypeValueHelper.getColor(ta, 1));
//                    View view = createView(MainActivity.this, name, attrs);
//                    view.setBackground(shapeDrawable);
//                    return view;
//                }
//                return null;
//            }
//        });
        setContentView(R.layout.activity_main);
    }

    private View createView(Context context, String name, AttributeSet attrs) {
        View view = null;
        try {
            if (-1 == name.indexOf('.')){
                if ("View".equals(name)) {
                    view = LayoutInflater.from(context).createView(name, "android.view.", attrs);
                }
                if (view == null) {
                    view = LayoutInflater.from(context).createView(name, "android.widget.", attrs);
                }
                if (view == null) {
                    view = LayoutInflater.from(context).createView(name, "android.webkit.", attrs);
                }
            }else {
                view = LayoutInflater.from(context).createView(name, null, attrs);
            }

        } catch (Exception e) {
            view = null;
        }
        return view;
    }
}
