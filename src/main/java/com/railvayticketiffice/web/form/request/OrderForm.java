package com.railvayticketiffice.web.form.request;

public class OrderForm {

    public OrderForm(int flightId, int wagonId, int seatId, int userId){
        this.flightId = flightId;
        this.wagonId = wagonId;
        this.seatId = seatId;
        this.userId = userId;
    }

    private int flightId;

    private int wagonId;

    private int seatId;

    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSeatId() {
        return seatId;
    }

    public int getFlightId() {
        return flightId;
    }

    public int getWagonId() {
        return wagonId;
    }

    public void setWagonId(int wagonId) {
        this.wagonId = wagonId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

}
