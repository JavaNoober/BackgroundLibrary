package com.noober.backgroudlibrary;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.noober.background.BLAutoInjectController;

/**
 * Created by xiaoqi on 2018/9/13
 */
public class MyApplication extends Application {



    {
        BLAutoInjectController.setEnableAutoInject(false);
    }
    //或者
    static {
        BLAutoInjectController.setEnableAutoInject(false);
    }
    //或者
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        BLAutoInjectController.setEnableAutoInject(false);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("BL", "onCreate");

//        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
//            @Override
//            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
//                BackgroundLibrary.inject(activity);
//            }
//
//            @Override
//            public void onActivityStarted(Activity activity) {
//
//            }
//
//            @Override
//            public void onActivityResumed(Activity activity) {
//
//            }
//
//            @Override
//            public void onActivityPaused(Activity activity) {
//
//            }
//
//            @Override
//            public void onActivityStopped(Activity activity) {
//
//            }
//
//            @Override
//            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
//
//            }
//
//            @Override
//            public void onActivityDestroyed(Activity activity) {
//
//            }
//        });
    }
}
