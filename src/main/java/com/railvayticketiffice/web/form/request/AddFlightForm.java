package com.railvayticketiffice.web.form.request;

import java.time.LocalDateTime;

public class AddFlightForm {

    private int departureStationId;

    private int arrivalStationId;

    private LocalDateTime departureDate;

    private LocalDateTime arrivalDate;

    private int trainId;

    private double cost;

    public AddFlightForm(int departureStationId, int arrivalStationId, LocalDateTime departureDate, LocalDateTime arrivalDate, int trainId , double cost) {
        this.departureStationId = departureStationId;
        this.arrivalStationId = arrivalStationId;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.trainId = trainId;
        this.cost = cost;
    }

    public int getDepartureStationId() {
        return departureStationId;
    }

    public void setDepartureStationId(int departureStationId) {
        this.departureStationId = departureStationId;
    }

    public int getArrivalStationId() {
        return arrivalStationId;
    }

    public void setArrivalStationId(int arrivalStationId) {
        this.arrivalStationId = arrivalStationId;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
