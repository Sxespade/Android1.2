package com.example.myapplication;

import java.io.Serializable;

public class Parcel2 implements Serializable {
    private int imageIndex;
    private String cityName;

    public int getImageIndex() {
        return imageIndex;
    }

    public String getCityName() {
        return cityName;
    }

    public Parcel2(int imageIndex, String cityName) {
        this.imageIndex = imageIndex;
        this.cityName = cityName;
    }
}
