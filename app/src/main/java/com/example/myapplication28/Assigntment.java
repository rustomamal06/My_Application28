package com.example.myapplication28;

import java.io.Serializable;
import java.util.Date;

public class Assigntment implements Serializable {
    protected String date;
    protected String time;
    protected String name;

    public Assigntment() {
    }


    public Assigntment(String date, String time,String name) {
        this.date = date;
        this.time = time;
        this.name=name;

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "date='" + date + '\'' +
                ", time='" + time + '\''
                +"name='"+name+'}';
    }
}


