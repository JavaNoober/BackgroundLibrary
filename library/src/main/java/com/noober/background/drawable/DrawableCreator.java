package com.noober.background.drawable;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

/**
 * Created by xiaoqi on 2018/10/22
 */
public class DrawableCreator {


    public enum Shape {

        Rectangle(0), Oval(1), Line(2), Ring(3);

        int value;

        Shape(int value) {
            this.value = value;
        }
    }

    public enum Gradient {

        Linear(0), Radial(1), Sweep(2);

        int value;

        Gradient(int value) {
            this.value = value;
        }
    }

    public static class Builder {
        private Shape shape = Shape.Rectangle;
        private int solidColor = -1;
        private float cornersRadius = -1;
        private float cornersBottomLeftRadius = -1;
        private float cornersBottomRightRadius = -1;
        private float cornersTopLeftRadius = -1;
        private float cornersTopRightRadius = -1;
        private int gradientAngle = -1;
        private float gradientCenterX = -1;
        private float gradientCenterY = -1;
        private int gradientCenterColor = -1;
        private int gradientEndColor = -1;
        private int gradientStartColor = -1;
        private float gradientRadius = -1;
        private Gradient gradient = Gradient.Linear;
        private boolean useLevel = false;
        private float paddingLeft = -1;
        private float paddingTop = -1;
        private float paddingRight = -1;
        private float paddingBottom = -1;
        private float sizeWidth = -1;
        private float sizeHeight = -1;
        private float strokeWidth = -1;
        private int strokeColor = -1;
        private float strokeDashWidth = -1;
        private float strokeDashGap = -1;
        private boolean rippleEnable = false;
        private int ripple_color = -1;

        private int checkableStrokeColor = -1;
        private int checkedStrokeColor = -1;
        private int enabledStrokeColor = -1;
        private int selectedStrokeColor = -1;
        private int pressedStrokeColor = -1;
        private int focusedStrokeColor = -1;
        private int unCheckableStrokeColor = -1;
        private int unCheckedStrokeColor = -1;
        private int unEnabledStrokeColor = -1;
        private int unSelectedStrokeColor = -1;
        private int unPressedStrokeColor = -1;
        private int unFocusedStrokeColor = -1;

        private Drawable checkableDrawable;
        private Drawable checkedDrawable;
        private Drawable enabledDrawable;
        private Drawable selectedDrawable;
        private Drawable pressedDrawable;
        private Drawable focusedDrawable;
        private Drawable focusedHovered;
        private Drawable focusedActivated;

        private Drawable unCheckableDrawable;
        private Drawable unCheckedDrawable;
        private Drawable unEnabledDrawable;
        private Drawable unSelectedDrawable;
        private Drawable unPressedDrawable;
        private Drawable unFocusedDrawable;
        private Drawable unFocusedHovered;
        private Drawable unFocusedActivated;

        private int checkableTextColor = -1;
        private int checkedTextColor = -1;
        private int enabledTextColor = -1;
        private int selectedTextColor = -1;
        private int pressedTextColor = -1;
        private int focusedTextColor = -1;

        private int unCheckableTextColor = -1;
        private int unCheckedTextColor = -1;
        private int unEnabledTextColor = -1;
        private int unSelectedTextColor = -1;
        private int unPressedTextColor = -1;
        private int unFocusedTextColor = -1;

        public Drawable build(){
            GradientDrawable drawable = new GradientDrawable();

            return drawable;
        }

    }
}
