package com.noober.backgroudlibrary;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.noober.background.BackgroundLibrary;
import com.noober.background.annotation.BLUsed;
import com.noober.background.drawable.DrawableCreator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().add(R.id.fl_content, new BlankFragment()).commitAllowingStateLoss();

        View vAnim = findViewById(R.id.v_anim);
//        AnimationDrawable animationDrawable = (AnimationDrawable) vAnim.getBackground();
//        animationDrawable.start();


//        findViewById(R.id.tv111).setSelected(true);

//        findViewById(R.id.select_view).setSelected(true);
//        findViewById(R.id.submit).setEnabled(false);
        Drawable drawable = new DrawableCreator.Builder().setCornersRadius(30)
                .setSolidColor(Color.parseColor("#FFFFFF"))
                .setStrokeColor(Color.parseColor("#FFFFFF"))
                .setStrokeWidth(10)
                .build();
        TextView tvTest1 = findViewById(R.id.tvTest1);
        tvTest1.setClickable(true);
        ColorStateList colors = new DrawableCreator.Builder().setPressedTextColor(Color.RED).setUnPressedTextColor(Color.BLUE).buildTextColor();
        tvTest1.setTextColor(colors);

        TextView btnTest2 = findViewById(R.id.btnTest2);
        btnTest2.setEnabled(false);
        GradientDrawable drawable2;
        drawable2 = new GradientDrawable();
        int[][] state = new int[2][];
        int[] color = new int[2];
        state[0] =  new int[]{android.R.attr.state_pressed};
        state[1] =  new int[]{-android.R.attr.state_pressed};
        color[0] = R.color.colorAccent;
        color[1] = R.color.colorPrimaryDark;
        ColorStateList colorStateList = new ColorStateList(state, color);
//        drawable2.setColor(colorStateList);
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


        TextView tvTest4 = findViewById(R.id.tvTest4);
        Drawable drawable4 = new DrawableCreator.Builder().setCornersRadius(dip2px(20))
                .setPressedDrawable(ContextCompat.getDrawable(this, R.drawable.circle_like_pressed))
                .setUnPressedDrawable(ContextCompat.getDrawable(this, R.drawable.circle_like_normal))
                .build();
        tvTest4.setClickable(true);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
            tvTest4.setBackground(drawable4);
        }else {
            tvTest4.setBackgroundDrawable(drawable4);
        }


        final Button btnLike = findViewById(R.id.btn_like);
        btnLike.setOnClickListener(new View.OnClickListener() {
            int i = 1;

            @Override
            public void onClick(View v) {
                btnLike.setText(String.format("点赞+%d", i++));
            }
        });
        final Button btnLike2 = findViewById(R.id.btn_like2);
        btnLike2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnLike2.isSelected()){
                    btnLike2.setText("未点赞");
                }else {
                    btnLike2.setText("已点赞");
                }
                btnLike2.setSelected(!btnLike2.isSelected());
            }
        });
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

    private void jumpToList(){
        startActivity(new Intent(MainActivity.this, ListActivity.class));
    }

}
