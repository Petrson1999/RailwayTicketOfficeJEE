package com.railvayticketiffice.services;

import com.railvayticketiffice.dao.jdbcdao.imp.JDBCStationDao;
import com.railvayticketiffice.dao.jdbcdao.interfaces.CrudGenericDao;
import com.railvayticketiffice.dao.jdbcdao.interfaces.StationDao;
import com.railvayticketiffice.entity.Station;
import com.railvayticketiffice.enums.DaoType;
import com.railvayticketiffice.exeptions.PersistException;
import com.railvayticketiffice.factory.DaoFactory;
import org.apache.log4j.Logger;

import java.util.List;

public class StationService {

    private static final Logger LOG = Logger.getLogger(StationService.class);

    private CrudGenericDao<Station, Integer> stationCrudDao;
    private StationDao  stationDao;


    public StationService() {
        this.stationCrudDao = DaoFactory.getEntityDao(DaoType.STATION);
        this.stationDao = new JDBCStationDao();
    }

    public List<Station> getAll() {
        List<Station> stations = null;
        try {
            stations = stationCrudDao.getAll();
        } catch (PersistException e) {
            LOG.error(e);
        }
        return stations;
    }

    public String getStationName(int stationId){
        String name = null;
        try {
            name = stationDao.getStationName(stationId);
        } catch (PersistException e) {
            LOG.error(e);
        }
        return name;
    }


}
