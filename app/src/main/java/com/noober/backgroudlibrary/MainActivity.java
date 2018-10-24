package com.noober.backgroudlibrary;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.noober.background.BackgroundLibrary;
import com.noober.background.drawable.DrawableCreator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        BackgroundLibrary.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().add(R.id.fl_content, new BlankFragment()).commitAllowingStateLoss();
        Button button = findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ListActivity.class));
            }
        });


        TextView tvTest1 = findViewById(R.id.tvTest1);
        tvTest1.setClickable(true);
        ColorStateList colors = new DrawableCreator.Builder().setPressedTextColor(Color.RED).setUnPressedTextColor(Color.BLUE).buildTextColor();
        tvTest1.setTextColor(colors);

        Button btnTest2 = findViewById(R.id.btnTest2);
        Drawable drawable = new DrawableCreator.Builder().setCornersRadius(dip2px(20))
                .setGradientAngle(0).setGradientColor(Color.parseColor("#63B8FF"), Color.parseColor("#4F94CD")).build();
        btnTest2.setBackground(drawable);

        Button btnTest3 = findViewById(R.id.btnTest3);
        Drawable drawable3 = new DrawableCreator.Builder().setCornersRadius(dip2px(20))
                .setRipple(true, Color.parseColor("#71C671"))
                .setSolidColor(Color.parseColor("#7CFC00"))
                .setStrokeColor(Color.parseColor("#8c6822"))
                .setStrokeWidth(dip2px(2))
                .build();
        btnTest3.setBackground(drawable3);

        TextView tvTest4 = findViewById(R.id.tvTest4);
        Drawable drawable4 = new DrawableCreator.Builder().setCornersRadius(dip2px(20))
                .setPressedDrawable(ContextCompat.getDrawable(this, R.drawable.circle_like_pressed))
                .setUnPressedDrawable(ContextCompat.getDrawable(this, R.drawable.circle_like_normal))
                .build();
        tvTest4.setClickable(true);
        tvTest4.setBackground(drawable4);
    }


    public int dip2px(float dipValue) {
        float scale = getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5F);
    }

}
