package com.railvayticketiffice.web.form.request;


public class AddTrainForm {
    private String trainName;

    public AddTrainForm(String trainName) {
        this.trainName = trainName;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }
}
