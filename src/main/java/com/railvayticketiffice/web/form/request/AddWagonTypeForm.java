package com.railvayticketiffice.web.form.request;

public class AddWagonTypeForm {

    private String name;

    private int seatsCount;


    public AddWagonTypeForm(String name, int seatsCount) {
        this.name = name;
        this.seatsCount = seatsCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeatsCount() {
        return seatsCount;
    }

    public void setSeatsCount(int seatsCount) {
        this.seatsCount = seatsCount;
    }
}
