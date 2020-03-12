package com.noober.background.common;

public enum MultiSelector {

    State_Checkable("state_checkable", android.R.attr.state_checkable),
    State_Checked("state_checked", android.R.attr.state_checked),
    State_Enabled("state_enabled", android.R.attr.state_enabled),
    State_Selected("state_selected", android.R.attr.state_selected),
    State_Pressed("state_pressed", android.R.attr.state_pressed),
    State_Focused("state_focused", android.R.attr.state_focused),
    State_Hovered("state_hovered", android.R.attr.state_hovered),
    State_Activated("state_activated", android.R.attr.state_activated);

    public String value;
    public int id;

    MultiSelector(String value, int id){
        this.value = value;
        this.id = id;
    }

    public static MultiSelector getMultiAttr(String value){
        switch (value){
            case "state_checkable":
                return State_Checkable;
            case "state_checked":
                return State_Checked;
            case "state_enabled":
                return State_Enabled;
            case "state_selected":
                return State_Selected;
            case "state_pressed":
                return State_Pressed;
            case "state_focused":
                return State_Focused;
            case "state_hovered":
                return State_Hovered;
            case "state_activated":
                return State_Activated;
            default:
                return null;
        }
    }
}
