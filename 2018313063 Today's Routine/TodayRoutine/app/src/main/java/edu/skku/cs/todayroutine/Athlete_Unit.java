package edu.skku.cs.todayroutine;

import java.io.Serializable;

public class Athlete_Unit implements Serializable {
    public boolean checked;
    public String unit_name;
    public Athlete_Unit(boolean checked, String unit_name){
        this.checked=checked;
        this.unit_name=unit_name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getUnit_name() {
        return unit_name;
    }

    public void setUnit_name(String unit_name) {
        this.unit_name = unit_name;
    }
}
