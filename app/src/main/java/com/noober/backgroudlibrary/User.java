package com.noober.backgroudlibrary;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by xiaoqi on 2018/9/27
 */
public class User extends BaseObservable {
    String name;


    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(com.noober.backgroudlibrary.BR.name);
    }
}
