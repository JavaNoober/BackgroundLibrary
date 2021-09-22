package com.noober.androidx;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.noober.background.drawable.DrawableCreator;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

public class MainActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main);
        Button button = findViewById(R.id.btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ListActivity.class));
            }
        });

//        AnimationDrawable animationDrawable = (AnimationDrawable) vAnim.getBackground();
//        animationDrawable.start();

        Drawable drawable = new DrawableCreator.Builder().setCornersRadius(30)
                .setSolidColor(Color.parseColor("#FFFFFF"))
                .setStrokeColor(Color.parseColor("#FFFFFF"))
                .setStrokeWidth(10)
                .build();
        TextView tvTest1 = findViewById(R.id.tvTest1);
        tvTest1.setClickable(true);
        ColorStateList colors = new DrawableCreator.Builder().setPressedTextColor(Color.RED).setUnPressedTextColor(Color.BLUE).buildTextColor();
        tvTest1.setTextColor(colors);

        Button btnTest2 = findViewById(R.id.btnTest2);
        Drawable drawable2 = new DrawableCreator.Builder().setCornersRadius(dip2px(20))
                .setGradientAngle(0).setGradientColor(Color.parseColor("#63B8FF"), Color.parseColor("#4F94CD")).build();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
            btnTest2.setBackground(drawable2);
        }else {
            btnTest2.setBackgroundDrawable(drawable2);
        }


        Button btnTest3 = findViewById(R.id.btnTest3);
        Drawable drawable3 = new DrawableCreator.Builder().setCornersRadius(dip2px(20))
                .setRipple(true, Color.parseColor("#71C671"))
                .setSolidColor(Color.parseColor("#7CFC00"))
                .setStrokeColor(Color.parseColor("#8c6822"))
                .setStrokeWidth(dip2px(2))
                .build();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
            btnTest3.setBackground(drawable3);
        }else {
            btnTest3.setBackgroundDrawable(drawable3);
        }


//        TextView tvTest4 = findViewById(R.id.tvTest4);
//        Drawable drawable4 = new DrawableCreator.Builder().setCornersRadius(dip2px(20))
//                .setPressedDrawable(ContextCompat.getDrawable(this, R.drawable.circle_like_pressed))
//                .setUnPressedDrawable(ContextCompat.getDrawable(this, R.drawable.circle_like_normal))
//                .build();
//        tvTest4.setClickable(true);
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
//            tvTest4.setBackground(drawable4);
//        }else {
//            tvTest4.setBackgroundDrawable(drawable4);
//        }

        TextView tvTest5 = findViewById(R.id.tvTest5);
        Drawable drawable5 = new DrawableCreator.Builder()
                .setSolidColor(Color.parseColor("#E3B666"))
                .setStrokeColor(Color.parseColor("#8c6822"))
                .setStrokeWidth(dip2px(0.5f))
                .build();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            tvTest5.setBackground(drawable5);
        } else {
            tvTest5.setBackgroundDrawable(drawable5);
        }

        TextView tvTest6 = findViewById(R.id.tvTest6);
        Drawable drawable6 = new DrawableCreator.Builder()
                .setSolidColor(Color.parseColor("#E3B666"))
                .setStrokeColor(Color.parseColor("#8c6822"))
                .setStrokeWidth(dip2px(0.5f))
                .setStrokePosition(DrawableCreator.StrokePosition.Right
                        | DrawableCreator.StrokePosition.Top
                        | DrawableCreator.StrokePosition.Bottom)
                .build();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            tvTest6.setBackground(drawable6);
        } else {
            tvTest6.setBackgroundDrawable(drawable6);
        }
        final Button btnEnable = findViewById(R.id.btn_setEnable);
        final TextView tvMulti = findViewById(R.id.tv_multi);
        btnEnable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tvMulti.isEnabled()){
                    tvMulti.setEnabled(false);
                    tvMulti.setText("textView一条属性多个状态：enable=false");
                }else {
                    tvMulti.setEnabled(true);
                    tvMulti.setText("textView一条属性多个状态：enable=true");
                }
            }
        });

    }


    public int dip2px(float dipValue) {
        float scale = getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5F);
    }

}
