package com.example.lesson4_recyclerview_27;

import java.io.Serializable;

public class Title implements Serializable {
    public String name;
    public String date;

    public Title(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
