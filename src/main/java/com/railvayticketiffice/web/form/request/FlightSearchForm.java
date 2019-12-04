package com.railvayticketiffice.web.form.request;

import java.util.Date;

public class FlightSearchForm {

    private int departureStationId;

    private int arrivalStationId;

    private Date dateTime;

    public FlightSearchForm(int departureStationId, int arrivalStationId , Date dateTime){
        this.departureStationId = departureStationId;
        this.arrivalStationId = arrivalStationId;
        this.dateTime = dateTime;
    }

    public Date getDateTime() {
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

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public void setDepartureStationId(int departureStationId) {
        this.departureStationId = departureStationId;
    }
}
