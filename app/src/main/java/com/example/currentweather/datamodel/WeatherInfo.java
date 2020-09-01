package com.example.currentweather.datamodel;

public class WeatherInfo {
    private String description;
    private int temp;
    private int minTemp;
    private int maxTemp;
    private int humidity;

    public String getDescription() {
        return description;
    }

    public WeatherInfo setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getTemp() {
        return temp;
    }

    public WeatherInfo setTemp(int temp) {
        this.temp = temp-273;
        return this;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public WeatherInfo setMinTemp(int minTemp) {
        this.minTemp = minTemp-273;
        return this;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public WeatherInfo setMaxTemp(int maxTemp) {
        this.maxTemp = maxTemp-273;
        return this;
    }

    public int getHumidity() {
        return humidity;
    }

    public WeatherInfo setHumidity(int humidity) {
        this.humidity = humidity;
        return this;
    }
}
