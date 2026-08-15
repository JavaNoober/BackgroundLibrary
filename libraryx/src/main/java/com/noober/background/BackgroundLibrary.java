package com.noober.background;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.LayoutInflaterCompat;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * minSdkVersion最小为14，建议minSdkVersion >= 16
 * 如果minSdkVersion < 16:bl_gradient_angle, bl_gradient_startColor, bl_gradient_centerColor, bl_gradient_endColor会失效，其他正常
 *
 * Created by xiaoqi on 2018/9/9
 */
public class BackgroundLibrary {

    private static volatile boolean rtlSupportEnabled = false;

    /**
     * 设置是否支持 RTL（从右到左）布局方向。默认关闭（false），开启后 left/right 相关属性
     * （圆角、内边距、描边定位、图标定位）会在 RTL 布局下自动镜像。
     */
    public static void setRtlSupportEnabled(boolean enabled) {
        rtlSupportEnabled = enabled;
    }

    public static boolean isRtlSupportEnabled() {
        return rtlSupportEnabled;
    }

    public static LayoutInflater inject(Context context) {
        LayoutInflater inflater;
        if (context instanceof Activity) {
            inflater = ((Activity) context).getLayoutInflater();
        } else {
            inflater = LayoutInflater.from(context);
        }
        if (inflater == null) {
            return null;
        }
        if (inflater.getFactory2() == null) {
            BackgroundFactory factory = setDelegateFactory(context);
            inflater.setFactory2(factory);
        } else if (!(inflater.getFactory2() instanceof BackgroundFactory)) {
            forceSetFactory2(inflater);
        }
        return inflater;
    }

    @NonNull
    private static BackgroundFactory setDelegateFactory(Context context) {
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
        return factory;
    }

    /**
     * used for activity which has addFactory
     * 如果因为其他库已经设置了factory，可以使用该方法去进行inject，在其他库的setFactory后面调用即可
     *
     * @param context
     */
    public static LayoutInflater inject2(Context context) {
        LayoutInflater inflater;
        if (context instanceof Activity) {
            inflater = ((Activity) context).getLayoutInflater();
        } else {
            inflater = LayoutInflater.from(context);
        }
        if (inflater == null) {
            return null;
        }
        forceSetFactory2(inflater);
        return inflater;
    }

    private static void forceSetFactory2(LayoutInflater inflater) {
        Class<LayoutInflaterCompat> compatClass = LayoutInflaterCompat.class;
        Class<LayoutInflater> inflaterClass = LayoutInflater.class;
        try {
            // androidx core 1.1.0+ 移除了 sCheckedField 字段，这里单独捕获忽略，
            // 避免影响后续 mFactory / mFactory2 的反射设置
            try {
                Field sCheckedField = compatClass.getDeclaredField("sCheckedField");
                sCheckedField.setAccessible(true);
                sCheckedField.setBoolean(compatClass, false);
            } catch (NoSuchFieldException ignored) {
                // 高版本 androidx core 无此字段，忽略即可
            }
            Field mFactory = inflaterClass.getDeclaredField("mFactory");
            mFactory.setAccessible(true);
            Field mFactory2 = inflaterClass.getDeclaredField("mFactory2");
            mFactory2.setAccessible(true);
            BackgroundFactory factory = new BackgroundFactory();
            if (inflater.getFactory2() != null) {
                factory.setInterceptFactory2(inflater.getFactory2());
            } else if (inflater.getFactory() != null) {
                factory.setInterceptFactory(inflater.getFactory());
            }
            mFactory2.set(inflater, factory);
            mFactory.set(inflater, factory);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
