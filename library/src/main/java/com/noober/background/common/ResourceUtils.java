package com.noober.background.common;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

public class ResourceUtils {

    @Nullable
    public static Drawable getDrawable(Context context, String resName) {
        Resources resources = context.getResources();
        if(resName.startsWith("#")){
            return new ColorDrawable(Color.parseColor(resName));
        }
        int id = resources.getIdentifier(resName, "drawable", context.getPackageName());
        if(id == 0){
            id = resources.getIdentifier(resName, "mipmap", context.getPackageName());
        }
        if(id == 0){
            id = resources.getIdentifier(resName, "color", context.getPackageName());
        }
        if(id == 0){
            return null;
        }
        return ContextCompat.getDrawable(context, id);
    }


    public static int getColor(Context context, String resName) {
        Resources resources = context.getResources();
        if(resName.startsWith("#")){
            return Color.parseColor(resName);
        }
        int id = resources.getIdentifier(resName, "color", context.getPackageName());
        if(id == 0){
            return -1;
        }
        return ContextCompat.getColor(context, id);
    }
}
