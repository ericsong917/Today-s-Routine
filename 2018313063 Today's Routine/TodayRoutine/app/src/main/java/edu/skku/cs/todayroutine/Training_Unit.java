package edu.skku.cs.todayroutine;

public class Training_Unit {
    public String unit_name;
    public String sets;
    public String minute;
    public String second;
    public boolean finished;
    public Training_Unit(String unit_name,String sets,String minute, String second, boolean finished){
        this.unit_name=unit_name;
        this.sets=sets;
        this.minute=minute;
        this.second=second;
        this.finished=finished;
    }

    public String getUnit_name() {
        return unit_name;
    }

    public void setUnit_name(String unit_name) {
        this.unit_name = unit_name;
    }

    public String getSets() {
        return sets;
    }

    public void setSets(String sets) {
        this.sets = sets;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
