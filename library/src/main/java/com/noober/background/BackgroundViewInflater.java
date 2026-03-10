package com.noober.background;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatViewInflater;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatMultiAutoCompleteTextView;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.noober.background.view.BLAutoCompleteTextView;
import com.noober.background.view.BLButton;
import com.noober.background.view.BLCheckBox;
import com.noober.background.view.BLCheckedTextView;
import com.noober.background.view.BLEditText;
import com.noober.background.view.BLImageButton;
import com.noober.background.view.BLImageView;
import com.noober.background.view.BLMultiAutoCompleteTextView;
import com.noober.background.view.BLRadioButton;
import com.noober.background.view.BLRatingBar;
import com.noober.background.view.BLSeekBar;
import com.noober.background.view.BLSpinner;
import com.noober.background.view.BLTextView;

/**
 * Custom ViewInflater to support IDE Preview for BackgroundLibrary.
 * It replaces native views with Preview versions that parse bl_ attributes during edit mode.
 *
 * @author: xTao
 * @date: 2026/1/9 17:55
 */
public class BackgroundViewInflater extends AppCompatViewInflater {
    @NonNull
    @Override
    protected AppCompatTextView createTextView(Context context, AttributeSet attrs) {
        inject(context);
        return super.createTextView(context, attrs);
    }

    @NonNull
    @Override
    protected AppCompatImageView createImageView(Context context, AttributeSet attrs) {
        inject(context);
        return super.createImageView(context, attrs);
    }

    @NonNull
    @Override
    protected AppCompatButton createButton(Context context, AttributeSet attrs) {
        inject(context);
        return super.createButton(context, attrs);
    }

    @NonNull
    @Override
    protected AppCompatEditText createEditText(Context context, AttributeSet attrs) {
        inject(context);
        return super.createEditText(context, attrs);
    }

    @NonNull
    @Override
    protected AppCompatSpinner createSpinner(Context context, AttributeSet attrs) {
        inject(context);
        return super.createSpinner(context, attrs);
    }

    @NonNull
    @Override
    protected AppCompatImageButton createImageButton(Context context, AttributeSet attrs) {
        inject(context);
        return super.createImageButton(context, attrs);
    }

    @NonNull
    @Override
    protected AppCompatCheckBox createCheckBox(Context context, AttributeSet attrs) {
        inject(context);
        return super.createCheckBox(context, attrs);
    }

    @NonNull
    @Override
    protected AppCompatRadioButton createRadioButton(Context context, AttributeSet attrs) {
        inject(context);
        return super.createRadioButton(context, attrs);
    }

    @NonNull
    @Override
    protected AppCompatCheckedTextView createCheckedTextView(Context context, AttributeSet attrs) {
        inject(context);
        return super.createCheckedTextView(context, attrs);
    }

    @NonNull
    @Override
    protected AppCompatAutoCompleteTextView createAutoCompleteTextView(Context context, AttributeSet attrs) {
        inject(context);
        return super.createAutoCompleteTextView(context, attrs);
    }

    @NonNull
    @Override
    protected AppCompatMultiAutoCompleteTextView createMultiAutoCompleteTextView(Context context, AttributeSet attrs) {
        inject(context);
        return super.createMultiAutoCompleteTextView(context, attrs);
    }

    @NonNull
    @Override
    protected AppCompatRatingBar createRatingBar(Context context, AttributeSet attrs) {
        inject(context);
        return super.createRatingBar(context, attrs);
    }

    @NonNull
    @Override
    protected AppCompatSeekBar createSeekBar(Context context, AttributeSet attrs) {
        inject(context);
        return super.createSeekBar(context, attrs);
    }

    @Nullable
    @Override
    protected View createView(Context context, String name, AttributeSet attrs) {
        inject(context);
        return super.createView(context, name, attrs);
    }

    private void inject(Context context) {
        BackgroundLibrary.inject(context);
    }
}
