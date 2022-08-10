package com.example.weatheringwithyou.ModelClass;

import java.util.List;

public class weatherData {
    private List<weather> weather;
    private main main;
    private String name;

    public weatherData(List<com.example.weatheringwithyou.ModelClass.weather> weather, com.example.weatheringwithyou.ModelClass.main main, String name) {
        this.weather = weather;
        this.main = main;
        this.name = name;
    }

    public List<com.example.weatheringwithyou.ModelClass.weather> getWeather() {
        return weather;
    }

    public void setWeather(List<com.example.weatheringwithyou.ModelClass.weather> weather) {
        this.weather = weather;
    }

    public com.example.weatheringwithyou.ModelClass.main getMain() {
        return main;
    }

    public void setMain(com.example.weatheringwithyou.ModelClass.main main) {
        this.main = main;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
