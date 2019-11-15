package com.vladimirpetrenkodev.railvayticketiffice.entity;


import inerfaces.Identified;

import java.util.Date;

public class Flight implements Identified<Integer> {

    private int id;

    private Station departureStation;

    private Station arrivalStation;

    private Date departureTime;

    private Date arrivalTime;

    private double cost;

    private String name;

    private int trainId;

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    public void setDepartureStation(Station departureStation) {
        this.departureStation = departureStation;
    }

    public Station getDepartureStation() {
        return departureStation;
    }

    public void setArrivalStation(Station arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public Station getArrivalStation() {
        return arrivalStation;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTrain(int trainId) {
        this.trainId = trainId;
    }

    public int getTrain() {
        return trainId;
    }
}
