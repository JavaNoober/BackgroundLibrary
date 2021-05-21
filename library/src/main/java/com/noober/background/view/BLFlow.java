package com.noober.background.view;

import android.content.Context;
import android.util.AttributeSet;

import com.noober.background.BackgroundFactory;

public class BLFlow extends android.support.constraint.helper.Flow {

	public BLFlow(Context context) {
		super(context);
	}

	public BLFlow(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	public BLFlow(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context, attrs);
	}

	private void init(Context context, AttributeSet attrs) {
		BackgroundFactory.setViewBackground(context, attrs, this);
	}
}
