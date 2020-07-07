package com.example.myapplication;

import androidx.appcompat.view.menu.MenuBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class Parcel implements Serializable {

    private static Parcel instance = null;

    private static final Object syncObj = new Object();

    private static String city;

    private static int count;

    public static List<String> data3 = new ArrayList<>();

    public static List<String> getData3() {
        return data3;
    }

    public static void setData3(List<String> data3) {
        Parcel.data3 = data3;
    }

    public void setCount(int count) {
        Parcel.count = count;
    }

    public int getCount() {
        return Parcel.count;
    }

    public int getCountPlus1() {
        Parcel.count++;
        return Parcel.count;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String chooseCity) {
        city = chooseCity;
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