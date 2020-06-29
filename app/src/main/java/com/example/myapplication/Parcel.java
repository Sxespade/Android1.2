package com.example.myapplication;

import java.io.Serializable;

public final class Parcel implements Serializable {

    private static Parcel instance = null;

    private static final Object syncObj = new Object();

    private String city;

    private Parcel(){
    }

    public String getCity() {
        return city;
    }

    public void setCity(String chooseCity) {
        this.city = chooseCity;
    }

    public static Parcel getInstance(){
        synchronized (syncObj) {
            if (instance == null) {
                instance = new Parcel();
            }
            return instance;
        }
    }
}