package com.noober.background.drawable;

import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.StyleableRes;

import com.noober.background.R;

public class AnimationDrawableCreator implements ICreateDrawable {

    private TypedArray animationTa;
    private int duration = 0;
    private AnimationDrawable drawable = new AnimationDrawable();

    public AnimationDrawableCreator(TypedArray animationTa) {
        this.animationTa = animationTa;
    }

    @Override
    public Drawable create() throws Exception {
        for(int i = 0; i < animationTa.getIndexCount(); i ++){
            int attr = animationTa.getIndex(i);
            if(attr == R.styleable.bl_anim_bl_duration){
                duration = animationTa.getInt(attr, 0);
            }else if(attr == R.styleable.bl_anim_bl_oneshot){
                drawable.setOneShot(animationTa.getBoolean(attr, false));
            }
        }
        if(animationTa.hasValue(R.styleable.bl_anim_bl_frame_drawable_item0)){
            Drawable itemDrawable = animationTa.getDrawable(R.styleable.bl_anim_bl_frame_drawable_item0);
            if(itemDrawable != null){
                if(animationTa.hasValue(R.styleable.bl_anim_bl_duration_item0)){
                    drawable.addFrame(itemDrawable, animationTa.getInt(R.styleable.bl_anim_bl_duration_item0, 0));
                }else {
                    drawable.addFrame(itemDrawable, duration);
                }
            }
        }
        addFrame(R.styleable.bl_anim_bl_frame_drawable_item0, R.styleable.bl_anim_bl_duration_item0);
        addFrame(R.styleable.bl_anim_bl_frame_drawable_item1, R.styleable.bl_anim_bl_duration_item1);
        addFrame(R.styleable.bl_anim_bl_frame_drawable_item2, R.styleable.bl_anim_bl_duration_item2);
        addFrame(R.styleable.bl_anim_bl_frame_drawable_item3, R.styleable.bl_anim_bl_duration_item3);
        addFrame(R.styleable.bl_anim_bl_frame_drawable_item4, R.styleable.bl_anim_bl_duration_item4);
        addFrame(R.styleable.bl_anim_bl_frame_drawable_item5, R.styleable.bl_anim_bl_duration_item5);
        addFrame(R.styleable.bl_anim_bl_frame_drawable_item6, R.styleable.bl_anim_bl_duration_item6);
        addFrame(R.styleable.bl_anim_bl_frame_drawable_item7, R.styleable.bl_anim_bl_duration_item7);
        addFrame(R.styleable.bl_anim_bl_frame_drawable_item8, R.styleable.bl_anim_bl_duration_item8);
        addFrame(R.styleable.bl_anim_bl_frame_drawable_item9, R.styleable.bl_anim_bl_duration_item9);
        addFrame(R.styleable.bl_anim_bl_frame_drawable_item10, R.styleable.bl_anim_bl_duration_item10);
        addFrame(R.styleable.bl_anim_bl_frame_drawable_item11, R.styleable.bl_anim_bl_duration_item11);
        addFrame(R.styleable.bl_anim_bl_frame_drawable_item12, R.styleable.bl_anim_bl_duration_item12);
        addFrame(R.styleable.bl_anim_bl_frame_drawable_item13, R.styleable.bl_anim_bl_duration_item13);
        addFrame(R.styleable.bl_anim_bl_frame_drawable_item14, R.styleable.bl_anim_bl_duration_item14);
        return drawable;
    }


    private void addFrame(@StyleableRes int itemDrawableId, @StyleableRes int itemDurationId){
        if(animationTa.hasValue(itemDrawableId)){
            Drawable itemDrawable = animationTa.getDrawable(itemDrawableId);
            if(itemDrawable != null){
                if(animationTa.hasValue(itemDurationId)){
                    drawable.addFrame(itemDrawable, animationTa.getInt(itemDurationId, 0));
                }else {
                    drawable.addFrame(itemDrawable, duration);
                }
            }
        }
    }
}
