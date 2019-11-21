package com.railvayticketiffice.entity;


public class WagonType implements Identified<Integer> {

    public WagonType() {
    }

    public WagonType(int id, int numberOfSeats, int comfort, String name) {
        this.id = id;
        this.numberOfSeats = numberOfSeats;
        this.comfort = comfort;
        this.name = name;
    }

    private int id;

    private int numberOfSeats;

    private int comfort;

    private String name;

    @Override
    public Integer getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public int getComfort() {
        return comfort;
    }

    public void setComfort(int comfort) {
        this.comfort = comfort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
