package com.railvayticketiffice.dao.jdbcdao.interfaces;

import com.railvayticketiffice.entity.Seat;
import com.railvayticketiffice.exeptions.PersistException;

import java.util.List;

public interface SeatDao {
    public List<Seat> getWagonSeats(int wagonId) throws PersistException;
}
