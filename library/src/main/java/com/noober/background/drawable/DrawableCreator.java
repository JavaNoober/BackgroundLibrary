package com.noober.background.drawable;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.annotation.NonNull;

import java.lang.reflect.Field;
import java.util.ArrayList;

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
        private Integer solidColor;
        private Float cornersRadius;
        private Float cornersBottomLeftRadius;
        private Float cornersBottomRightRadius;
        private Float cornersTopLeftRadius;
        private Float cornersTopRightRadius;
        private int gradientAngle = -1;
        private Float gradientCenterX;
        private Float gradientCenterY;
        private Integer gradientCenterColor;
        private Integer gradientEndColor;
        private Integer gradientStartColor;
        private Float gradientRadius;
        private Gradient gradient = Gradient.Linear;
        private boolean useLevel = false;

        private Rect padding = new Rect();

        private Float sizeWidth;
        private Float sizeHeight;
        private Float strokeWidth;
        private Integer strokeColor;
        private float strokeDashWidth = 0;
        private float strokeDashGap = 0;
        private boolean rippleEnable = false;
        private Integer rippleColor;

        private Integer checkableStrokeColor;
        private Integer checkedStrokeColor;
        private Integer enabledStrokeColor;
        private Integer selectedStrokeColor;
        private Integer pressedStrokeColor;
        private Integer focusedStrokeColor;
        private Integer unCheckableStrokeColor;
        private Integer unCheckedStrokeColor;
        private Integer unEnabledStrokeColor;
        private Integer unSelectedStrokeColor;
        private Integer unPressedStrokeColor;
        private Integer unFocusedStrokeColor;

        private Integer checkableSolidColor;
        private Integer checkedSolidColor;
        private Integer enabledSolidColor;
        private Integer selectedSolidColor;
        private Integer pressedSolidColor;
        private Integer focusedSolidColor;
        private Integer unCheckableSolidColor;
        private Integer unCheckedSolidColor;
        private Integer unEnabledSolidColor;
        private Integer unSelectedSolidColor;
        private Integer unPressedSolidColor;
        private Integer unFocusedSolidColor;

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

        private Integer checkableTextColor;
        private Integer checkedTextColor;
        private Integer enabledTextColor;
        private Integer selectedTextColor;
        private Integer pressedTextColor;
        private Integer focusedTextColor;

        private Integer unCheckableTextColor;
        private Integer unCheckedTextColor;
        private Integer unEnabledTextColor;
        private Integer unSelectedTextColor;
        private Integer unPressedTextColor;
        private Integer unFocusedTextColor;
        private int textColorCount;

        private boolean hasSelectDrawable = false;

        public Builder setShape(Shape shape) {
            this.shape = shape;
            return this;
        }

        public Builder setSolidColor(int solidColor) {
            this.solidColor = solidColor;
            return this;
        }

        public Builder setCornersRadius(float cornersRadius) {
            this.cornersRadius = cornersRadius;
            return this;
        }

        public Builder setCornersRadius(float cornersBottomLeftRadius, float cornersBottomRightRadius, float cornersTopLeftRadius, float cornersTopRightRadius) {
            this.cornersBottomLeftRadius = cornersBottomLeftRadius;
            this.cornersBottomRightRadius = cornersBottomRightRadius;
            this.cornersTopLeftRadius = cornersTopLeftRadius;
            this.cornersTopRightRadius = cornersTopRightRadius;
            return this;
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        public Builder setGradientAngle(int gradientAngle) {
            this.gradientAngle = gradientAngle;
            return this;
        }

        public Builder setGradientCenterXY(float gradientCenterX, float gradientCenterY) {
            this.gradientCenterX = gradientCenterX;
            this.gradientCenterY = gradientCenterY;
            return this;
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        public Builder setGradientColor(int startColor, int endColor) {
            this.gradientStartColor = startColor;
            this.gradientEndColor = endColor;
            return this;
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        public Builder setGradientColor(int startColor, int centerColor, int endColor) {
            this.gradientStartColor = startColor;
            this.gradientCenterColor = centerColor;
            this.gradientEndColor = endColor;
            return this;
        }

        public Builder setGradientRadius(float gradientRadius) {
            this.gradientRadius = gradientRadius;
            return this;
        }

        public Builder setGradient(Gradient gradient) {
            this.gradient = gradient;
            return this;
        }

        public Builder setUseLevel(boolean useLevel) {
            this.useLevel = useLevel;
            return this;
        }

        public Builder setPadding(float paddingLeft, float paddingTop, float paddingRight, float paddingBottom) {
            padding.left = (int) paddingLeft;
            padding.top = (int) paddingTop;
            padding.right = (int) paddingRight;
            padding.bottom = (int) paddingBottom;
            return this;
        }

        public Builder setSizeWidth(float sizeWidth) {
            this.sizeWidth = sizeWidth;
            return this;
        }

        public Builder setSizeHeight(float sizeHeight) {
            this.sizeHeight = sizeHeight;
            return this;
        }

        public Builder setStrokeWidth(float strokeWidth) {
            this.strokeWidth = strokeWidth;
            return this;
        }

        public Builder setStrokeColor(int strokeColor) {
            this.strokeColor = strokeColor;
            return this;
        }

        public Builder setStrokeDashWidth(float strokeDashWidth) {
            this.strokeDashWidth = strokeDashWidth;
            return this;
        }

        public Builder setStrokeDashGap(float strokeDashGap) {
            this.strokeDashGap = strokeDashGap;
            return this;
        }

        public Builder setRipple(boolean rippleEnable, int rippleColor) {
            this.rippleEnable = rippleEnable;
            this.rippleColor = rippleColor;
            return this;
        }

        public Builder setCheckableStrokeColor(int checkableStrokeColor, int unCheckableStrokeColor) {
            this.checkableStrokeColor = checkableStrokeColor;
            this.unCheckableStrokeColor = unCheckableStrokeColor;
            return this;
        }

        public Builder setCheckedStrokeColor(int checkedStrokeColor, int unCheckedStrokeColor) {
            this.checkedStrokeColor = checkedStrokeColor;
            this.unCheckedStrokeColor = unCheckedStrokeColor;
            return this;
        }

        public Builder setEnabledStrokeColor(int enabledStrokeColor, int unEnabledStrokeColor) {
            this.enabledStrokeColor = enabledStrokeColor;
            this.unEnabledStrokeColor = unEnabledStrokeColor;
            return this;
        }

        public Builder setSelectedStrokeColor(int selectedStrokeColor, int unSelectedStrokeColor) {
            this.selectedStrokeColor = selectedStrokeColor;
            this.unSelectedStrokeColor = unSelectedStrokeColor;
            return this;
        }

        public Builder setPressedStrokeColor(int pressedStrokeColor, int unPressedStrokeColor) {
            this.pressedStrokeColor = pressedStrokeColor;
            this.unPressedStrokeColor = unPressedStrokeColor;
            return this;
        }

        public Builder setFocusedStrokeColor(int focusedStrokeColor, int unFocusedStrokeColor) {
            this.focusedStrokeColor = focusedStrokeColor;
            this.unFocusedStrokeColor = unFocusedStrokeColor;
            return this;
        }

        public Builder setCheckableSolidColor(int checkableSolidColor, int unCheckableSolidColor) {
            this.checkableSolidColor = checkableSolidColor;
            this.unCheckableSolidColor = unCheckableSolidColor;
            return this;
        }

        public Builder setCheckedSolidColor(int checkedSolidColor, int unCheckedSolidColor) {
            this.checkedSolidColor = checkedSolidColor;
            this.unCheckedSolidColor = unCheckedSolidColor;
            return this;
        }

        public Builder setEnabledSolidColor(int enabledSolidColor, int unEnabledSolidColor) {
            this.enabledSolidColor = enabledSolidColor;
            this.unEnabledSolidColor = unEnabledSolidColor;
            return this;
        }

        public Builder setSelectedSolidColor(int selectedSolidColor, int unSelectedSolidColor) {
            this.selectedSolidColor = selectedSolidColor;
            this.unSelectedSolidColor = unSelectedSolidColor;
            return this;
        }

        public Builder setPressedSolidColor(int pressedSolidColor, int unPressedSolidColor) {
            this.pressedSolidColor = pressedSolidColor;
            this.unPressedSolidColor = unPressedSolidColor;
            return this;
        }

        public Builder setFocusedSolidColor(int focusedSolidColor, int unFocusedSolidColor) {
            this.focusedSolidColor = focusedSolidColor;
            this.unFocusedSolidColor = unFocusedSolidColor;
            return this;
        }

        public Builder setCheckableDrawable(Drawable checkableDrawable) {
            this.hasSelectDrawable = true;
            this.checkableDrawable = checkableDrawable;
            return this;
        }

        public Builder setCheckedDrawable(Drawable checkedDrawable) {
            this.hasSelectDrawable = true;
            this.checkedDrawable = checkedDrawable;
            return this;
        }

        public Builder setEnabledDrawable(Drawable enabledDrawable) {
            this.hasSelectDrawable = true;
            this.enabledDrawable = enabledDrawable;
            return this;
        }

        public Builder setSelectedDrawable(Drawable selectedDrawable) {
            this.hasSelectDrawable = true;
            this.selectedDrawable = selectedDrawable;
            return this;
        }

        public Builder setPressedDrawable(Drawable pressedDrawable) {
            this.hasSelectDrawable = true;
            this.pressedDrawable = pressedDrawable;
            return this;
        }

        public Builder setFocusedDrawable(Drawable focusedDrawable) {
            this.hasSelectDrawable = true;
            this.focusedDrawable = focusedDrawable;
            return this;
        }

        public Builder setFocusedHovered(Drawable focusedHovered) {
            this.hasSelectDrawable = true;
            this.focusedHovered = focusedHovered;
            return this;
        }

        public Builder setFocusedActivated(Drawable focusedActivated) {
            this.hasSelectDrawable = true;
            this.focusedActivated = focusedActivated;
            return this;
        }

        public Builder setUnCheckableDrawable(Drawable unCheckableDrawable) {
            this.hasSelectDrawable = true;
            this.unCheckableDrawable = unCheckableDrawable;
            return this;
        }

        public Builder setUnCheckedDrawable(Drawable unCheckedDrawable) {
            this.hasSelectDrawable = true;
            this.unCheckedDrawable = unCheckedDrawable;
            return this;
        }

        public Builder setUnEnabledDrawable(Drawable unEnabledDrawable) {
            this.hasSelectDrawable = true;
            this.unEnabledDrawable = unEnabledDrawable;
            return this;
        }

        public Builder setUnSelectedDrawable(Drawable unSelectedDrawable) {
            this.hasSelectDrawable = true;
            this.unSelectedDrawable = unSelectedDrawable;
            return this;
        }

        public Builder setUnPressedDrawable(Drawable unPressedDrawable) {
            this.hasSelectDrawable = true;
            this.unPressedDrawable = unPressedDrawable;
            return this;
        }

        public Builder setUnFocusedDrawable(Drawable unFocusedDrawable) {
            this.hasSelectDrawable = true;
            this.hasSelectDrawable = true;
            this.unFocusedDrawable = unFocusedDrawable;
            return this;
        }

        public Builder setUnFocusedHovered(Drawable unFocusedHovered) {
            this.hasSelectDrawable = true;
            this.unFocusedHovered = unFocusedHovered;
            return this;
        }

        public Builder setUnFocusedActivated(Drawable unFocusedActivated) {
            this.hasSelectDrawable = true;
            this.unFocusedActivated = unFocusedActivated;
            return this;
        }

        public Builder setCheckableTextColor(int checkableTextColor) {
            this.checkableTextColor = checkableTextColor;
            this.textColorCount++;
            return this;
        }

        public Builder setCheckedTextColor(int checkedTextColor) {
            this.checkedTextColor = checkedTextColor;
            this.textColorCount++;
            return this;
        }

        public Builder setEnabledTextColor(int enabledTextColor) {
            this.enabledTextColor = enabledTextColor;
            this.textColorCount++;
            return this;
        }

        public Builder setSelectedTextColor(int selectedTextColor) {
            this.selectedTextColor = selectedTextColor;
            this.textColorCount++;
            return this;
        }

        public Builder setPressedTextColor(int pressedTextColor) {
            this.pressedTextColor = pressedTextColor;
            this.textColorCount++;
            return this;
        }

        public Builder setFocusedTextColor(int focusedTextColor) {
            this.focusedTextColor = focusedTextColor;
            this.textColorCount++;
            return this;
        }

        public Builder setUnCheckableTextColor(int unCheckableTextColor) {
            this.unCheckableTextColor = unCheckableTextColor;
            this.textColorCount++;
            return this;
        }

        public Builder setUnCheckedTextColor(int unCheckedTextColor) {
            this.unCheckedTextColor = unCheckedTextColor;
            this.textColorCount++;
            return this;
        }

        public Builder setUnEnabledTextColor(int unEnabledTextColor) {
            this.unEnabledTextColor = unEnabledTextColor;
            this.textColorCount++;
            return this;
        }

        public Builder setUnSelectedTextColor(int unSelectedTextColor) {
            this.unSelectedTextColor = unSelectedTextColor;
            this.textColorCount++;
            return this;
        }

        public Builder setUnPressedTextColor(int unPressedTextColor) {
            this.unPressedTextColor = unPressedTextColor;
            this.textColorCount++;
            return this;
        }

        public Builder setUnFocusedTextColor(int unFocusedTextColor) {
            this.unFocusedTextColor = unFocusedTextColor;
            this.textColorCount++;
            return this;
        }

        public Drawable build() {
            GradientDrawable drawable = null;
            StateListDrawable stateListDrawable = null;
            if (hasSelectDrawable) {
                stateListDrawable = getStateListDrawable();
            } else {
                drawable = getGradientDrawable();
            }
            if (rippleEnable && rippleColor != null) {
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Drawable contentDrawable = (stateListDrawable == null ? drawable : stateListDrawable);
                    return new RippleDrawable(ColorStateList.valueOf(rippleColor), contentDrawable, contentDrawable);
                } else {
                    StateListDrawable resultDrawable = new StateListDrawable();
                    GradientDrawable unPressDrawable = getGradientDrawable();
                    unPressDrawable.setColor(rippleColor);
                    resultDrawable.addState(new int[]{-android.R.attr.state_pressed}, drawable);
                    resultDrawable.addState(new int[]{android.R.attr.state_pressed}, unPressDrawable);
                    return resultDrawable;
                }
            }

            return drawable == null ? stateListDrawable : drawable;
        }

        public ColorStateList buildTextColor() {
            if (textColorCount > 0) {
                return getColorStateList();
            } else {
                return null;
            }
        }

        private ColorStateList getColorStateList() {
            int[][] states = new int[textColorCount][];
            int[] colors = new int[textColorCount];
            int index = 0;
            if (checkableTextColor != null) {
                states[index] = new int[]{android.R.attr.state_checkable};
                colors[index] = checkableTextColor;
                index++;
            }
            if (unCheckableTextColor != null) {
                states[index] = new int[]{-android.R.attr.state_checkable};
                colors[index] = unCheckableTextColor;
                index++;
            }
            if (checkedTextColor != null) {
                states[index] = new int[]{android.R.attr.state_checked};
                colors[index] = checkedTextColor;
                index++;
            }
            if (unCheckedTextColor != null) {
                states[index] = new int[]{-android.R.attr.state_checked};
                colors[index] = unCheckedTextColor;
                index++;
            }
            if (enabledTextColor != null) {
                states[index] = new int[]{android.R.attr.state_enabled};
                colors[index] = enabledTextColor;
                index++;
            }
            if (unEnabledTextColor != null) {
                states[index] = new int[]{-android.R.attr.state_enabled};
                colors[index] = unEnabledTextColor;
                index++;
            }
            if (selectedTextColor != null) {
                states[index] = new int[]{android.R.attr.state_selected};
                colors[index] = selectedTextColor;
                index++;
            }
            if (unSelectedTextColor != null) {
                states[index] = new int[]{-android.R.attr.state_selected};
                colors[index] = unSelectedTextColor;
                index++;
            }
            if (pressedTextColor != null) {
                states[index] = new int[]{android.R.attr.state_pressed};
                colors[index] = pressedTextColor;
                index++;
            }
            if (unPressedTextColor != null) {
                states[index] = new int[]{-android.R.attr.state_pressed};
                colors[index] = unPressedTextColor;
                index++;
            }
            if (focusedTextColor != null) {
                states[index] = new int[]{android.R.attr.state_focused};
                colors[index] = focusedTextColor;
                index++;
            }
            if (unFocusedTextColor != null) {
                states[index] = new int[]{-android.R.attr.state_focused};
                colors[index] = unFocusedTextColor;
            }
            return new ColorStateList(states, colors);
        }

        private StateListDrawable getStateListDrawable() {
            StateListDrawable stateListDrawable = null;
            if (checkableDrawable != null) {
                stateListDrawable = getStateListDrawable(stateListDrawable);
                stateListDrawable.addState(new int[]{android.R.attr.state_checkable}, checkableDrawable);
            }
            if (unCheckableDrawable != null) {
                stateListDrawable = getStateListDrawable(stateListDrawable);
                stateListDrawable.addState(new int[]{-android.R.attr.state_checkable}, unCheckableDrawable);
            }
            if (checkedDrawable != null) {
                stateListDrawable = getStateListDrawable(stateListDrawable);
                stateListDrawable.addState(new int[]{android.R.attr.state_checked}, checkedDrawable);
            }
            if (unCheckedDrawable != null) {
                stateListDrawable = getStateListDrawable(stateListDrawable);
                stateListDrawable.addState(new int[]{-android.R.attr.state_checked}, unCheckedDrawable);
            }
            if (enabledDrawable != null) {
                stateListDrawable = getStateListDrawable(stateListDrawable);
                stateListDrawable.addState(new int[]{android.R.attr.state_enabled}, enabledDrawable);
            }
            if (unEnabledDrawable != null) {
                stateListDrawable = getStateListDrawable(stateListDrawable);
                stateListDrawable.addState(new int[]{-android.R.attr.state_enabled}, unEnabledDrawable);
            }
            if (selectedDrawable != null) {
                stateListDrawable = getStateListDrawable(stateListDrawable);
                stateListDrawable.addState(new int[]{android.R.attr.state_selected}, selectedDrawable);
            }
            if (unSelectedDrawable != null) {
                stateListDrawable = getStateListDrawable(stateListDrawable);
                stateListDrawable.addState(new int[]{-android.R.attr.state_selected}, unSelectedDrawable);
            }
            if (pressedDrawable != null) {
                stateListDrawable = getStateListDrawable(stateListDrawable);
                stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, pressedDrawable);
            }
            if (unPressedDrawable != null) {
                stateListDrawable = getStateListDrawable(stateListDrawable);
                stateListDrawable.addState(new int[]{-android.R.attr.state_pressed}, unPressedDrawable);
            }
            if (focusedDrawable != null) {
                stateListDrawable = getStateListDrawable(stateListDrawable);
                stateListDrawable.addState(new int[]{android.R.attr.state_focused}, focusedDrawable);
            }
            if (unFocusedDrawable != null) {
                stateListDrawable = getStateListDrawable(stateListDrawable);
                stateListDrawable.addState(new int[]{-android.R.attr.state_focused}, unFocusedDrawable);
            }
            if (focusedHovered != null) {
                stateListDrawable = getStateListDrawable(stateListDrawable);
                stateListDrawable.addState(new int[]{android.R.attr.state_hovered}, focusedHovered);
            }
            if (unFocusedHovered != null) {
                stateListDrawable = getStateListDrawable(stateListDrawable);
                stateListDrawable.addState(new int[]{-android.R.attr.state_hovered}, unFocusedHovered);
            }
            if (focusedActivated != null) {
                stateListDrawable = getStateListDrawable(stateListDrawable);
                stateListDrawable.addState(new int[]{android.R.attr.state_activated}, focusedActivated);
            }
            if (unFocusedActivated != null) {
                stateListDrawable = getStateListDrawable(stateListDrawable);
                stateListDrawable.addState(new int[]{-android.R.attr.state_activated}, unFocusedActivated);
            }
            return stateListDrawable;
        }

        @NonNull
        private GradientDrawable getGradientDrawable() {
            GradientDrawable drawable = new GradientDrawable();
            drawable.setShape(shape.value);

            if (cornersRadius != null) {
                drawable.setCornerRadius(cornersRadius);
            }

            if (cornersBottomLeftRadius != null && cornersBottomRightRadius != null &&
                    cornersTopLeftRadius != null && cornersTopRightRadius != null) {
                float[] cornerRadius = new float[8];
                cornerRadius[0] = cornersTopLeftRadius;
                cornerRadius[1] = cornersTopLeftRadius;
                cornerRadius[2] = cornersTopRightRadius;
                cornerRadius[3] = cornersTopRightRadius;
                cornerRadius[4] = cornersBottomRightRadius;
                cornerRadius[5] = cornersBottomRightRadius;
                cornerRadius[6] = cornersBottomLeftRadius;
                cornerRadius[7] = cornersBottomLeftRadius;
                drawable.setCornerRadii(cornerRadius);
            }

            if (gradient == Gradient.Linear && gradientAngle != -1) {
                gradientAngle %= 360;
                if (gradientAngle % 45 == 0) {
                    GradientDrawable.Orientation mOrientation = GradientDrawable.Orientation.LEFT_RIGHT;
                    switch (gradientAngle) {
                        case 0:
                            mOrientation = GradientDrawable.Orientation.LEFT_RIGHT;
                            break;
                        case 45:
                            mOrientation = GradientDrawable.Orientation.BL_TR;
                            break;
                        case 90:
                            mOrientation = GradientDrawable.Orientation.BOTTOM_TOP;
                            break;
                        case 135:
                            mOrientation = GradientDrawable.Orientation.BR_TL;
                            break;
                        case 180:
                            mOrientation = GradientDrawable.Orientation.RIGHT_LEFT;
                            break;
                        case 225:
                            mOrientation = GradientDrawable.Orientation.TR_BL;
                            break;
                        case 270:
                            mOrientation = GradientDrawable.Orientation.TOP_BOTTOM;
                            break;
                        case 315:
                            mOrientation = GradientDrawable.Orientation.TL_BR;
                            break;
                    }
                    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        drawable.setOrientation(mOrientation);
                    }

                }
            }

            if (gradientCenterX != null && gradientCenterY != null) {
                drawable.setGradientCenter(gradientCenterX, gradientCenterY);
            }
            if (gradientStartColor != null && gradientEndColor != null) {
                int[] colors;
                if (gradientCenterColor != null) {
                    colors = new int[3];
                    colors[0] = gradientStartColor;
                    colors[1] = gradientCenterColor;
                    colors[2] = gradientEndColor;
                } else {
                    colors = new int[2];
                    colors[0] = gradientStartColor;
                    colors[1] = gradientEndColor;
                }
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    drawable.setColors(colors);
                }
            }
            if (gradientRadius != null) {
                drawable.setGradientRadius(gradientRadius);
            }
            drawable.setGradientType(gradient.value);
            drawable.setUseLevel(useLevel);
            if (!padding.isEmpty()) {

                try {
                    Field paddingField = drawable.getClass().getField("mPadding");
                    paddingField.setAccessible(true);
                    paddingField.set(drawable, padding);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
            if (sizeWidth != null && sizeHeight != null) {
                drawable.setSize(sizeWidth.intValue(), sizeHeight.intValue());
            }

            if (strokeWidth != null && strokeWidth > 0) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    int start = 0;
                    ArrayList<Integer> stateList = new ArrayList<>();
                    ArrayList<Integer> colorList = new ArrayList<>();
                    if (pressedStrokeColor != null && unPressedStrokeColor != null) {
                        stateList.add(android.R.attr.state_pressed);
                        stateList.add(-android.R.attr.state_pressed);
                        colorList.add(pressedStrokeColor);
                        colorList.add(unPressedStrokeColor);
                    }
                    if (checkableStrokeColor != null && unCheckableStrokeColor != null) {
                        stateList.add(android.R.attr.state_checkable);
                        stateList.add(-android.R.attr.state_checkable);
                        colorList.add(checkableStrokeColor);
                        colorList.add(unCheckableStrokeColor);
                    }
                    if (checkedStrokeColor != null && unCheckedStrokeColor != null) {
                        stateList.add(android.R.attr.state_checked);
                        stateList.add(-android.R.attr.state_checked);
                        colorList.add(checkedStrokeColor);
                        colorList.add(unCheckedStrokeColor);
                    }
                    if (enabledStrokeColor != null && unEnabledStrokeColor != null) {
                        stateList.add(android.R.attr.state_enabled);
                        stateList.add(-android.R.attr.state_enabled);
                        colorList.add(enabledStrokeColor);
                        colorList.add(unEnabledStrokeColor);
                    }
                    if (selectedStrokeColor != null && unSelectedStrokeColor != null) {
                        stateList.add(android.R.attr.state_selected);
                        stateList.add(-android.R.attr.state_selected);
                        colorList.add(selectedStrokeColor);
                        colorList.add(unSelectedStrokeColor);
                    }
                    if (focusedStrokeColor != null && unFocusedStrokeColor != null) {
                        stateList.add(android.R.attr.state_focused);
                        stateList.add(-android.R.attr.state_focused);
                        colorList.add(focusedStrokeColor);
                        colorList.add(unFocusedStrokeColor);
                    }
                    if (stateList.size() > 0) {
                        int[][] state = new int[stateList.size()][];
                        int[] color = new int[stateList.size()];
                        for (int iState : stateList) {
                            state[start] = new int[]{iState};
                            color[start] = colorList.get(start);
                            start++;
                        }

                        ColorStateList colorStateList = new ColorStateList(state, color);
                        drawable.setStroke(strokeWidth.intValue(), colorStateList, strokeDashWidth, strokeDashGap);
                    } else if (strokeColor != null) {
                        drawable.setStroke(strokeWidth.intValue(), strokeColor, strokeDashWidth, strokeDashGap);
                    }
                    stateList = null;
                    colorList = null;
                } else if (strokeColor != null) {
                    drawable.setStroke(strokeWidth.intValue(), strokeColor, strokeDashWidth, strokeDashGap);
                }
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                int start = 0;
                ArrayList<Integer> stateList = new ArrayList<>();
                ArrayList<Integer> colorList = new ArrayList<>();
                if (pressedSolidColor != null && unPressedSolidColor != null) {
                    stateList.add(android.R.attr.state_pressed);
                    stateList.add(-android.R.attr.state_pressed);
                    colorList.add(pressedSolidColor);
                    colorList.add(unPressedSolidColor);
                }
                if (checkableSolidColor != null && unCheckableSolidColor != null) {
                    stateList.add(android.R.attr.state_checkable);
                    stateList.add(-android.R.attr.state_checkable);
                    colorList.add(checkableSolidColor);
                    colorList.add(unCheckableSolidColor);
                }
                if (checkedSolidColor != null && unCheckedSolidColor != null) {
                    stateList.add(android.R.attr.state_checked);
                    stateList.add(-android.R.attr.state_checked);
                    colorList.add(checkedSolidColor);
                    colorList.add(unCheckedSolidColor);
                }
                if (enabledSolidColor != null && unEnabledSolidColor != null) {
                    stateList.add(android.R.attr.state_enabled);
                    stateList.add(-android.R.attr.state_enabled);
                    colorList.add(enabledSolidColor);
                    colorList.add(unEnabledSolidColor);
                }
                if (selectedSolidColor != null && unSelectedSolidColor != null) {
                    stateList.add(android.R.attr.state_selected);
                    stateList.add(-android.R.attr.state_selected);
                    colorList.add(selectedSolidColor);
                    colorList.add(unSelectedSolidColor);
                }
                if (focusedSolidColor != null && unFocusedSolidColor != null) {
                    stateList.add(android.R.attr.state_focused);
                    stateList.add(-android.R.attr.state_focused);
                    colorList.add(focusedSolidColor);
                    colorList.add(unFocusedSolidColor);
                }
                if (stateList.size() > 0) {
                    int[][] state = new int[stateList.size()][];
                    int[] color = new int[stateList.size()];
                    for (int iState : stateList) {
                        state[start] = new int[]{iState};
                        color[start] = colorList.get(start);
                        start++;
                    }
                    ColorStateList colorStateList = new ColorStateList(state, color);
                    drawable.setColor(colorStateList);
                } else if (solidColor != null) {
                    drawable.setColor(solidColor);
                }
                stateList = null;
                colorList = null;
            } else if (solidColor != null) {
                drawable.setColor(solidColor);
            }
            return drawable;
        }


        StateListDrawable getStateListDrawable(StateListDrawable stateListDrawable) {
            if (stateListDrawable == null) {
                stateListDrawable = new StateListDrawable();
            }
            return stateListDrawable;
        }

    }
}
