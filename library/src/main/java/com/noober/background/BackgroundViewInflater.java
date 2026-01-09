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
    private static final String TAG = "BackgroundViewInflater";

    @NonNull
    @Override
    protected AppCompatTextView createTextView(Context context, AttributeSet attrs) {
        return new BLTextView(context, attrs);
    }

    @NonNull
    @Override
    protected AppCompatImageView createImageView(Context context, AttributeSet attrs) {
        return new BLImageView(context, attrs);
    }

    @NonNull
    @Override
    protected AppCompatButton createButton(Context context, AttributeSet attrs) {
        return new BLButton(context, attrs);
    }

    @NonNull
    @Override
    protected AppCompatEditText createEditText(Context context, AttributeSet attrs) {
        return new BLEditText(context, attrs);
    }

    @NonNull
    @Override
    protected AppCompatSpinner createSpinner(Context context, AttributeSet attrs) {
        return new BLSpinner(context, attrs);
    }

    @NonNull
    @Override
    protected AppCompatImageButton createImageButton(Context context, AttributeSet attrs) {
        return new BLImageButton(context, attrs);
    }

    @NonNull
    @Override
    protected AppCompatCheckBox createCheckBox(Context context, AttributeSet attrs) {
        return new BLCheckBox(context, attrs);
    }

    @NonNull
    @Override
    protected AppCompatRadioButton createRadioButton(Context context, AttributeSet attrs) {
        return new BLRadioButton(context, attrs);
    }

    @NonNull
    @Override
    protected AppCompatCheckedTextView createCheckedTextView(Context context, AttributeSet attrs) {
        return new BLCheckedTextView(context, attrs);
    }

    @NonNull
    @Override
    protected AppCompatAutoCompleteTextView createAutoCompleteTextView(Context context, AttributeSet attrs) {
        return new BLAutoCompleteTextView(context, attrs);
    }

    @NonNull
    @Override
    protected AppCompatMultiAutoCompleteTextView createMultiAutoCompleteTextView(Context context, AttributeSet attrs) {
        return new BLMultiAutoCompleteTextView(context, attrs);
    }

    @NonNull
    @Override
    protected AppCompatRatingBar createRatingBar(Context context, AttributeSet attrs) {
        return new BLRatingBar(context, attrs);
    }

    @NonNull
    @Override
    protected AppCompatSeekBar createSeekBar(Context context, AttributeSet attrs) {
        return new BLSeekBar(context, attrs);
    }

    @Nullable
    @Override
    protected View createView(Context context, String name, AttributeSet attrs) {
        if (name.startsWith("com.noober.background.view")) {
            return null;
        }

        LayoutInflater inflater = BackgroundLibrary.inject(context);
        if (inflater == null) {
            return null;
        }
        View view = null;
        if (inflater.getFactory2() != null) {
            view = inflater.getFactory2().onCreateView(name, context, attrs);
            if (view == null) {
                view = inflater.getFactory2().onCreateView(null, name, context, attrs);
            }
        } else if (inflater.getFactory() != null) {
            view = inflater.getFactory().onCreateView(name, context, attrs);
        }
        return BackgroundFactory.setViewBackground(name, context, attrs, view);
    }
}
