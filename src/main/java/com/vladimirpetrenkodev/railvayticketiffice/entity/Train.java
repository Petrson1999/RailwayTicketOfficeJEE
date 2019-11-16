package com.vladimirpetrenkodev.railvayticketiffice.entity;

import inerfaces.Identified;

public class Train implements Identified<Integer> {

    public Train(){}

    public Train(int id, String name, int locomotiveId){
        this.id = id;
        this.name = name;
        this.locomotiveId = locomotiveId;
    }

    private int id;

    private String name;

    private int locomotiveId;

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLocomotiveId(int locomotiveId) {
        this.locomotiveId = locomotiveId;
    }

    public int getLocomotiveId() {
        return locomotiveId;
    }

}
