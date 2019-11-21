package com.railvayticketiffice.entity;

public class Luggage implements Identified<Integer> {

    public Luggage() {
    }

    public Luggage(int id, int ticketId) {
        this.id = id;
        this.ticketId = id;
    }

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
