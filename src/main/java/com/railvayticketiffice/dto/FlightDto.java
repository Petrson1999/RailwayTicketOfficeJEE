package com.railvayticketiffice.dto;

import java.util.Date;

public class FlightDto {

    private int id;

    private String departureStation;

    private String arrivalStation;

    private Date departureTime;

    private Date arrivalTime;

    private double cost;

    private String name;

    private String trainName;

    private int freeSeatNumber;

    public int getFreeSeatNumber() {
        return freeSeatNumber;
    }

    public void setFreeSeatNumber(int freeSeatNumber) {
        this.freeSeatNumber = freeSeatNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalStation(String arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public void setDepartureStation(String departureStation) {
        this.departureStation = departureStation;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public int getId() {
        return id;
    }

    public String getArrivalStation() {
        return arrivalStation;
    }

    public String getDepartureStation() {
        return departureStation;
    }

    public String getTrainName() {
        return trainName;
    }
}
