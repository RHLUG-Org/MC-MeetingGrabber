package com.jasomo.util;

public class MeetingInfo {
    String date, numeric_date, time;
    public MeetingInfo(String date, String numeric_date, String time){
        this.date = date;
        this.numeric_date = numeric_date;
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public String getNumeric_date() {
        return numeric_date;
    }

    public String getTime() {
        return time;
    }
}
