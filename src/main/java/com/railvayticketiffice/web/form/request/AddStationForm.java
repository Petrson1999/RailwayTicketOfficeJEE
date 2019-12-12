package com.railvayticketiffice.web.form.request;

public class AddStationForm {

    private String stationName;

    public AddStationForm(String stationName) {
        this.stationName = stationName;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
}
