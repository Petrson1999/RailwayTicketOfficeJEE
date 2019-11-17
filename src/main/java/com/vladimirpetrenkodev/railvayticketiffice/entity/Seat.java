package com.vladimirpetrenkodev.railvayticketiffice.entity;

public class Seat implements Identified<Integer> {

    public Seat() {
    }

    public Seat(int id, int wagonId, int placeNumber) {
        this.id = id;
        this.wagonId = wagonId;
        this.placeNumber = placeNumber;
    }

    private int id;

    private int wagonId;

    private int placeNumber;

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    public void setWagonId(int wagonId) {
        this.wagonId = wagonId;
    }

    public int getWagonId() {
        return wagonId;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(int placeNumber) {
        this.placeNumber = placeNumber;
    }
}
