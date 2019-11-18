package com.vladimirpetrenkodev.railvayticketiffice.entity;

public class Ticket implements Identified<Integer> {

    public Ticket(){}

    public Ticket(int id, int flightId, int userId, double cost, int seatId, String status){
        this.id = id;
        this.flightId = flightId;
        this.userId = userId;
        this.cost = cost;
        this.seatId = seatId;
        this.status = status;
    }

    private int id;

    private int flightId;

    private int userId;

    private double cost;

    private int seatId;

    private String status;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatIdId(int seatId) {
        this.seatId = seatId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
