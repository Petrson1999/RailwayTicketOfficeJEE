package com.railvayticketiffice.dao.jdbcdao.interfaces;

import com.railvayticketiffice.exeptions.PersistException;

/**
 * Interface with methods for station data management
 *
 * @author Vladimir Petrenko
 */
public interface StationDao {
    /**
     * get station name by station id
     *
     * @param stationId station id
     *
     * @return station name by station id
     */
    public String getStationName(int stationId) throws PersistException;
}
