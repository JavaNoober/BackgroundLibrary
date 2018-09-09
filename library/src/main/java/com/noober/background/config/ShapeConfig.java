package com.noober.background.config;

public enum ShapeConfig {

    Rectangle(0), Oval(1), Line(2), Ring(3);

    public int value;

    ShapeConfig(int value){
        this.value = value;
    }

    public static ShapeConfig get(int value){
        if (value == 1){
            return Oval;
        }else if (value == 2){
            return Line;
        }else if (value == 3){
            return Ring;
        }else {
            return Rectangle;
        }
    }
}
