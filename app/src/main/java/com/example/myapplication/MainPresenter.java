package com.example.myapplication;



public final class MainPresenter {

    private static MainPresenter instance = null;

    private static final Object syncObj = new Object();

    private String chooseCity;

    private MainPresenter(){
        chooseCity = "Для сохранения данных при повороте";
    }

    public String getChooseCity() {
        return chooseCity;
    }

    public void setChooseCity(String chooseCity) {
        this.chooseCity = chooseCity;
    }

    public static MainPresenter getInstance(){
        synchronized (syncObj) {
            if (instance == null) {
                instance = new MainPresenter();
            }
            return instance;
        }
    }
}
