package com.noober.androidx;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

public class MainActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}
