package com.example.a_daybingo;

public class BingoItem {
    String task;
    boolean checked;
    int timesChecked, timesTallied;
    public BingoItem (String task, int timesChecked){
        this.task = task;
        checked = false;
        this.timesChecked = timesChecked;
        timesTallied = 0;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked() {
        checked = !checked;
    }

    public String getTask() {
        return task;
    }

    public int getTimesChecked() {
        return timesChecked;
    }

    public void addTimesChecked() {
        timesChecked++;
    }

    public int getTimesTallied() {
        return timesTallied;
    }

    public void addTimesTallied(){
        timesTallied++;
    }

    public void minusTimesTallied(){
        timesTallied--;
    }
}
