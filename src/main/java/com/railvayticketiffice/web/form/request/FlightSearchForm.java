package com.railvayticketiffice.web.form.request;

import java.time.LocalDateTime;

public class FlightSearchForm {

    private int departureStationId;

    private int arrivalStationId;

    private LocalDateTime dateTime;

    public FlightSearchForm(int departureStationId, int arrivalStationId , LocalDateTime dateTime){
        this.departureStationId = departureStationId;
        this.arrivalStationId = arrivalStationId;
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public int getArrivalStationId() {
        return arrivalStationId;
    }

    public int getDepartureStationId() {
        return departureStationId;
    }

    public void setArrivalStationId(int arrivalStationId) {
        this.arrivalStationId = arrivalStationId;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDepartureStationId(int departureStationId) {
        this.departureStationId = departureStationId;
    }
}
