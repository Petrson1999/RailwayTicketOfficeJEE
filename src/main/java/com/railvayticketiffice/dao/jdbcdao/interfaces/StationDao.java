package com.railvayticketiffice.dao.jdbcdao.interfaces;

import com.railvayticketiffice.exeptions.PersistException;

public interface StationDao {
    public String getStationName(int stationId) throws PersistException;
}
