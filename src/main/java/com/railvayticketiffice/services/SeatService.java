package com.railvayticketiffice.services;


import com.railvayticketiffice.dao.jdbcdao.imp.JDBCSeatDao;
import com.railvayticketiffice.dao.jdbcdao.interfaces.CrudGenericDao;
import com.railvayticketiffice.dao.jdbcdao.interfaces.SeatDao;
import com.railvayticketiffice.dto.SeatsDto;
import com.railvayticketiffice.entity.Seat;
import com.railvayticketiffice.entity.Wagon;
import com.railvayticketiffice.enums.DaoType;
import com.railvayticketiffice.exeptions.PersistException;
import com.railvayticketiffice.factory.DaoFactory;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeatService {
    private static final Logger LOG = Logger.getLogger(SeatService.class);

    private CrudGenericDao<Seat, Integer> seatCrudDao;
    private SeatDao seatDao;


    public SeatService() {
        this.seatCrudDao = DaoFactory.getEntityDao(DaoType.SEAT);
        this.seatDao = new JDBCSeatDao();
    }

    public List<Seat> getWagonSeats(int wagonId){
        List<Seat> seats = null;
        try {
            seats = seatDao.getWagonSeats(wagonId);
        } catch (PersistException e) {
            LOG.error(e);
        }
        return seats;
    }

    public List<Seat> getAll(){
        List<Seat> seats = null;
        try {
            seats = seatCrudDao.getAll();
        } catch (PersistException e) {
            LOG.error(e);
        }
        return seats;
    }

    public boolean addSeat(Seat seat){
        try {
            this.seatCrudDao.persist(seat);
            return true;
        } catch (PersistException e) {
            LOG.error(e);
            return false;
        }
    }
}
