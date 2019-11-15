package com.vladimirpetrenkodev.railvayticketiffice.entity;


import inerfaces.Identified;

public class Locomotive extends CompoundTrains implements Identified<Integer> {

    private int id;

    private String name;

    @Override
    public Integer getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

}
