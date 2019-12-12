package com.railvayticketiffice.dao.jdbcdao.interfaces;

import com.railvayticketiffice.entity.Seat;
import com.railvayticketiffice.exeptions.PersistException;

import java.util.List;

/**
 * Interface with methods for seat data management
 *
 * @author Vladimir Petrenko
 */
public interface SeatDao {
    /**
     * get seats by wagon
     *
     * @return List seats
     */
    public List<Seat> getWagonSeats(int wagonId) throws PersistException;
}
