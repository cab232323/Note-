package com.example.mechrevo.note;

import java.io.Serializable;

public class Diary implements Serializable {
    private String date;
    private String cont;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }
}
