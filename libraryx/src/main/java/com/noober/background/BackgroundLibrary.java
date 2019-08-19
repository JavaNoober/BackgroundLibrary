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
            forceSetFactory2(inflater, context);
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
        forceSetFactory2(inflater, context);
        return inflater;
    }

    /**
     * 通过LayoutInflaterCompat去规避非SDK接口在Android Q 中的受限。
     * 首先：设置LayoutInflaterCompat的sCheckedField为false， 保证可以设置当前mFactory2的值
     * 第二：设置LayoutInflater的 mFactory 为空，保证LayoutInflaterCompat调用setFactory的时候不进行FactoryMerger操作
     * 第三：反射调用LayoutInflaterCompat的forceSetFactory2方法
     * 第四：重新设置LayoutInflater的mFactory值，防止调用Fragment的时候fragment会进行FactoryMerger操作
     * 经过上述步骤，在Activity以及Activity中的Fragment就会变成我们想要的factory类
     */
    private static void forceSetFactory2(LayoutInflater inflater, Context context) {
        Class<LayoutInflaterCompat> compatClass = LayoutInflaterCompat.class;
        Class<LayoutInflater> inflaterClass = LayoutInflater.class;
        try {
            Field sCheckedField = compatClass.getDeclaredField("sCheckedField");
            sCheckedField.setAccessible(true);
            sCheckedField.setBoolean(inflater, false);
            Field mFactory = inflaterClass.getDeclaredField("mFactory");
            mFactory.setAccessible(true);
            mFactory.set(inflater, null);

            Method method = compatClass.getDeclaredMethod("forceSetFactory2", LayoutInflater.class, LayoutInflater.Factory2.class);
            method.setAccessible(true);
            BackgroundFactory factory = setDelegateFactory(context);
            method.invoke(null, inflater, factory);
            mFactory.set(inflater, factory);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
//        try {
//            Field field = LayoutInflater.class.getDeclaredField("mFactorySet");
//            field.setAccessible(true);
//            field.setBoolean(inflater, false);
//
//            BackgroundFactory factory = new BackgroundFactory();
//            if (inflater.getFactory2() != null) {
//                factory.setInterceptFactory2(inflater.getFactory2());
//            } else if (inflater.getFactory() != null) {
//                factory.setInterceptFactory(inflater.getFactory());
//            }
//            inflater.setFactory2(factory);
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalArgumentException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        LayoutInflaterCompat.setFactory2(inflater.cloneInContext(context), setDelegateFactory(context));
    }
}
