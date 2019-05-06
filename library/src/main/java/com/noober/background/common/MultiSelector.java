package com.noober.background.common;

public enum MultiSelector {

    State_Checkable("state_checkable", android.R.attr.state_checkable),
    State_Checked("state_checkable", android.R.attr.state_checkable),
    State_Enabled("state_checkable", android.R.attr.state_enabled),
    State_Selected("state_checkable", android.R.attr.state_selected),
    State_Pressed("state_checkable", android.R.attr.state_pressed),
    State_Focused("state_checkable", android.R.attr.state_focused),
    State_Hovered("state_checkable", android.R.attr.state_hovered),
    State_Activated("state_checkable", android.R.attr.state_activated);

    public String value;
    public int id;

    MultiSelector(String value, int id){
        this.value = value;
        this.id = id;
    }
}
