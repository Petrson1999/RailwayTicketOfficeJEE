package com.vladimirpetrenkodev.railvayticketiffice.entity;


import inerfaces.Identified;

public class Wagon extends CompoundTrains implements Identified<Integer> {

    public Wagon() {
    }

    public Wagon(int id, int trainId, int wagonTypeId, String name) {
        this.id = id;
        this.setTrainId(trainId);
        this.wagonTypeId = wagonTypeId;
        this.name = name;
    }

    private int id;

    private int wagonTypeId;

    private String name;

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    public void setWagonTypeId(int wogonTypeId) {
        this.wagonTypeId = wogonTypeId;
    }

    public int getWagonTypeId() {
        return wagonTypeId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
