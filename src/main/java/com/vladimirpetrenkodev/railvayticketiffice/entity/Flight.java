package com.vladimirpetrenkodev.railvayticketiffice.entity;


import inerfaces.Identified;

import java.util.Date;

public class Flight implements Identified<Integer> {

    public Flight() {
    }

    public Flight(int id, int departureStationId, int arrivalStationId, Date departureTime, Date arrivalTime,
                  double cost, String name, int trainId) {
        this.id = id;
        this.departureStationId = departureStationId;
        this.arrivalStationId = arrivalStationId;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.cost = cost;
        this.name = name;
        this.trainId = trainId;
    }

    private int id;

    private int departureStationId;

    private int arrivalStationId;

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

    public void setDepartureStationID(int departureStationId) {
        this.departureStationId = departureStationId;
    }

    public int getDepartureStationId() {
        return departureStationId;
    }

    public void setArrivalStationId(int arrivalStationId) {
        this.arrivalStationId = arrivalStationId;
    }

    public int getArrivalStationId() {
        return arrivalStationId;
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

    public int getTrainId() {
        return trainId;
    }
}
