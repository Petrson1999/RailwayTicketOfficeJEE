package com.vladimirpetrenkodev.railvayticketiffice.entity;

import inerfaces.Identified;

public class Ticket implements Identified<Integer> {

    private int id;

    private int flightId;

    private int userId;

    private double cost;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getFlight() {
        return flightId;
    }

    public void setFlight(int flightId) {
        this.flightId = flightId;
    }

    public int getUser() {
        return userId;
    }

    public void setUser(int userId) {
        this.userId = userId;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
