package com.noober.background;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

public class BackgroundLibrary {

    public static void inject(Context context) {
        LayoutInflater inflater;
        if(context instanceof Activity){
            inflater = ((Activity)context).getLayoutInflater();
        }else {
            inflater = LayoutInflater.from(context);
        }
        BackgroundFactory factory = new BackgroundFactory();
        if (context instanceof AppCompatActivity) {
            final AppCompatDelegate delegate = ((AppCompatActivity) context).getDelegate();
            factory.setInterceptFactory(new LayoutInflater.Factory() {
                @Override
                public View onCreateView(String name, Context context, AttributeSet attrs) {
                    return delegate.createView(null, name, context, attrs);
                }
            });
        }
        inflater.setFactory(factory);
    }
}
