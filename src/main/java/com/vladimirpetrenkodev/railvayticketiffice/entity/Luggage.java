package com.vladimirpetrenkodev.railvayticketiffice.entity;


import inerfaces.Identified;

public class Luggage implements Identified<Integer> {

    private int id;

    private int ticketId;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getTicketId() {
        return ticketId;
    }
}
