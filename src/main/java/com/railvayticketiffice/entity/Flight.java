package com.railvayticketiffice.entity;


import java.time.LocalDateTime;

public class Flight implements Identified<Integer> {

    public Flight() {
    }

    public Flight(int id, int departureStationId, int arrivalStationId, LocalDateTime departureTime, LocalDateTime arrivalTime,
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

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

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

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalDateTime getArrivalTime() {
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
