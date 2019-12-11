package com.railvayticketiffice.dto;

import com.railvayticketiffice.entity.Ticket;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TicketDto extends Ticket {

    public TicketDto(int id, int flightId, int userId, double cost, int seatId, String departureStation,
                     String arrivalStation, String trainName, String wagonNumber, int seatNumber ,
                     LocalDateTime departureTime, LocalDateTime arrivalTime) {
        this.setId(id);
        this.setFlightId(flightId);
        this.setUserId(userId);
        this.setCost(cost);
        this.setSeatIdId(seatId);
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.trainName = trainName;
        this.wagonNumber = wagonNumber;
        this.seatNumber = seatNumber;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.formatedDepartureTime = departureTime.format(formatter);
        this.formatedArrivalTime = arrivalTime.format(formatter);
    }

    private String departureStation;

    private String arrivalStation;

    private String trainName;

    private String wagonNumber;

    private int seatNumber;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    private String formatedDepartureTime;

    private String formatedArrivalTime;


    public String getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(String departureStation) {
        this.departureStation = departureStation;
    }

    public String getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(String arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getWagonNumber() {
        return wagonNumber;
    }

    public void setWagonNumber(String wagonNumber) {
        this.wagonNumber = wagonNumber;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getFormatedDepartureTime() {
        return formatedDepartureTime;
    }

    public void setFormatedDepartureTime(String formatedDepartureTime) {
        this.formatedDepartureTime = formatedDepartureTime;
    }

    public String getFormatedArrivalTime() {
        return formatedArrivalTime;
    }

    public void setFormatedArrivalTime(String formatedArrivalTime) {
        this.formatedArrivalTime = formatedArrivalTime;
    }
}
