package com.vladimirpetrenkodev.railvayticketiffice.entity;

import inerfaces.Identified;

import java.util.ArrayList;

public class Train implements Identified<Integer> {

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
