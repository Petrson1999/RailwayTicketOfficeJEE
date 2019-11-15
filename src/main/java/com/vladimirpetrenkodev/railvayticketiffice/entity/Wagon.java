package com.vladimirpetrenkodev.railvayticketiffice.entity;


import inerfaces.Identified;

public class Wagon extends CompoundTrains  implements Identified<Integer> {

    private int id;

    private int wogonTypeId;

    private String name;

    public void setId(int id){
        this.id = id;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    public void setWogonTypeId(int wogonTypeId) {
        this.wogonTypeId = wogonTypeId;
    }

    public int getWogonTypeId() {
        return wogonTypeId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
