package com.railvayticketiffice.dto;

import com.railvayticketiffice.entity.Flight;

import java.time.LocalDateTime;

public class FlightDto extends Flight {

    public FlightDto(int id, int departureStationId, int arrivalStationId, LocalDateTime departureTime, LocalDateTime arrivalTime,
                     double cost, String name, int trainId, String departureStation, String arrivalStation,
                     String trainName, int freeSeatNumber) {
        this.setId(id);
        this.setDepartureStationID(departureStationId);
        this.setArrivalStationId(arrivalStationId);
        this.setDepartureTime(departureTime);
        this.setArrivalTime(arrivalTime);
        this.setCost(cost);
        this.setName(name);
        this.setTrain(trainId);
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.trainName = trainName;
        this.freeSeatNumber = freeSeatNumber;

    }

    private String departureStation;

    private String arrivalStation;

    private String trainName;

    private int freeSeatNumber;

    public int getFreeSeatNumber() {
        return freeSeatNumber;
    }

    public void setFreeSeatNumber(int freeSeatNumber) {
        this.freeSeatNumber = freeSeatNumber;
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
