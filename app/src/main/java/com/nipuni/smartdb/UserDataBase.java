package com.nipuni.smartdb;

public class UserDataBase {

    String name;
    String tp;


    public UserDataBase() {
    }

    public UserDataBase(String name, String tp) {
        this.name = name;
        this.tp = tp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }
}

