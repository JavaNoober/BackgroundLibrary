package com.noober.background.drawable;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;

import java.util.ArrayList;

/**
 * 生成带状态的颜色列表（ColorStateList）。
 * <p>
 * 修复 checked/unChecked 等状态遮蔽 enabled/unEnabled 的问题：ColorStateList 的匹配是
 * 「从前往后取第一个满足的状态项」，旧实现把 checked 与 enabled 各自作为孤立单状态项、
 * 且 checked 排在前面，导致 checkable 控件（RadioButton/CheckBox）永远命中 checked/unChecked
 * 项，enabled/unEnabled 永远不生效。
 * <p>
 * 本类的兼容保证：只有当 enabled 系列属性（正/负任一）被设置时才启用组合语义；否则按
 * 传入的顺序模式，生成与旧实现逐项一致的单状态项（含历史状态映射），不影响历史老用户。
 */
public final class StateColorListHelper {

    private StateColorListHelper() {
    }

    /**
     * 一个可二分的状态维度（正/负）。
     */
    public static final class Dimension {
        final int posAttr;
        final int negAttr;
        final int posState;
        final int negState;

        public Dimension(int posAttr, int negAttr, int posState, int negState) {
            this.posAttr = posAttr;
            this.negAttr = negAttr;
            this.posState = posState;
            this.negState = negState;
        }
    }

    /**
     * @param ta              TypedArray
     * @param dimensions      正状态维度列表，按优先级从高到低传入（不含 enabled）
     * @param positiveFirst   true 表示正负相邻（如 solid/stroke 旧顺序）；false 表示正全在前、负全在后（如 text 旧顺序）
     * @param enabledPosAttr  enabled 正色 attr
     * @param enabledNegAttr  unEnabled 负色 attr
     * @param defaultColorAttr 默认态（无状态）颜色 attr，传 0 表示没有
     * @return 没有任何状态色时为 null
     */
    public static ColorStateList create(TypedArray ta,
                                        Dimension[] dimensions,
                                        boolean positiveFirst,
                                        int enabledPosAttr,
                                        int enabledNegAttr,
                                        int defaultColorAttr) {
        boolean enabledSet = ta.hasValue(enabledPosAttr) || ta.hasValue(enabledNegAttr);
        // 仅当 enabled 正色被设置时，才把其他维度与 enabled 组合
        int enabledState = ta.hasValue(enabledPosAttr) ? android.R.attr.state_enabled : 0;

        ArrayList<int[]> states = new ArrayList<>();
        ArrayList<Integer> colors = new ArrayList<>();

        // 1. 禁用态一票覆盖
        if (enabledSet && ta.hasValue(enabledNegAttr)) {
            add(states, colors, new int[]{-android.R.attr.state_enabled},
                    ta.getColor(enabledNegAttr, 0));
        }

        // 2. 正状态维度
        if (positiveFirst) {
            for (Dimension d : dimensions) {
                addPositive(ta, d, enabledState, states, colors);
                addNegative(ta, d, enabledState, states, colors);
            }
        } else {
            for (Dimension d : dimensions) {
                addPositive(ta, d, enabledState, states, colors);
            }
            for (Dimension d : dimensions) {
                addNegative(ta, d, enabledState, states, colors);
            }
        }

        // 3. enabled 正色兜底
        if (enabledSet && ta.hasValue(enabledPosAttr)) {
            add(states, colors, new int[]{android.R.attr.state_enabled},
                    ta.getColor(enabledPosAttr, 0));
        }

        if (states.isEmpty()) {
            return null;
        }

        // 默认态（无状态）兜底色
        if (defaultColorAttr != 0 && ta.hasValue(defaultColorAttr)) {
            add(states, colors, new int[]{}, ta.getColor(defaultColorAttr, 0));
        }

        int size = states.size();
        int[][] stateSet = new int[size][];
        int[] colorSet = new int[size];
        for (int i = 0; i < size; i++) {
            stateSet[i] = states.get(i);
            colorSet[i] = colors.get(i);
        }
        return new ColorStateList(stateSet, colorSet);
    }

    private static void addPositive(TypedArray ta, Dimension d, int enabledState,
                                    ArrayList<int[]> states, ArrayList<Integer> colors) {
        if (ta.hasValue(d.posAttr)) {
            add(states, colors, buildState(d.posState, enabledState), ta.getColor(d.posAttr, 0));
        }
    }

    private static void addNegative(TypedArray ta, Dimension d, int enabledState,
                                    ArrayList<int[]> states, ArrayList<Integer> colors) {
        if (ta.hasValue(d.negAttr)) {
            add(states, colors, buildState(d.negState, enabledState), ta.getColor(d.negAttr, 0));
        }
    }

    private static int[] buildState(int stateFlag, int enabledState) {
        if (enabledState != 0) {
            return new int[]{stateFlag, enabledState};
        }
        return new int[]{stateFlag};
    }

    private static void add(ArrayList<int[]> states, ArrayList<Integer> colors, int[] state, int color) {
        states.add(state);
        colors.add(color);
    }
}