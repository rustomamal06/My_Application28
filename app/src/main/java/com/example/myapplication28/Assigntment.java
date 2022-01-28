package com.example.myapplication28;

import java.util.Date;

public class Assigntment {
    private Date duedate;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    private String category;
    private String name;

    public Assigntment(Date duedate, String catgory) {
        this.duedate = duedate;
        this.category= catgory;
    }

    public Assigntment() {
    }

    public Date getDuedate() {
        return duedate;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }

    public String getC() {
        return category;
    }

    public void setC(String category) {
        this.category= category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
