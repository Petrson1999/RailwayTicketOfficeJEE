package com.vladimirpetrenkodev.railvayticketiffice.entity;

public class Station implements Identified<Integer> {

    public Station() {
    }

    public Station(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private int id;

    private String name;

    @Override
    public Integer getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
