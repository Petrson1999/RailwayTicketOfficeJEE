package com.vladimirpetrenkodev.railvayticketiffice.entity;

import inerfaces.Identified;

public class Seat implements Identified<Integer> {

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
