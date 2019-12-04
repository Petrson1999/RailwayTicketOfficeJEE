package com.railvayticketiffice.dto;

import com.railvayticketiffice.entity.Seat;
import com.railvayticketiffice.entity.Wagon;

import java.util.List;

public class WagonDto extends Wagon {

    public WagonDto(Wagon wagon, String wagonType , List<Seat> seats){
        this.setId(wagon.getId());
        this.setTrainId(wagon.getTrainId());
        this.setWagonTypeId(wagon.getWagonTypeId());
        this.setName(wagon.getName());
        this.wagonType = wagonType;
        this.seats = seats;
    }

    private String wagonType;

    private List<Seat> seats;

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public String getWagonType() {
        return wagonType;
    }

    public void setWagonType(String wagonType) {
        this.wagonType = wagonType;
    }
}
