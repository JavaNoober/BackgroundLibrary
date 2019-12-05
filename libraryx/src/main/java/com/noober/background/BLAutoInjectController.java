package com.noober.background;

/**
 * 控制是否自动进行inject
 */
public class BLAutoInjectController {

    private static boolean enableAutoInject = true;

    public static boolean isEnableAutoInject() {
        return enableAutoInject;
    }

    public static void setEnableAutoInject(boolean enableAutoInject) {
        BLAutoInjectController.enableAutoInject = enableAutoInject;
    }
}
