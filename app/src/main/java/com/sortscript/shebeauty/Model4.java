package com.sortscript.shebeauty;

public class Model4 {
    String Date, Time, Title;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Model4(String date, String time, String title) {
        Date = date;
        Time = time;
        this.Title = title;


    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public Model4() {


    }
}
