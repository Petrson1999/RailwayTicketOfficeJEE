package com.railvayticketiffice.services;

import com.railvayticketiffice.dao.jdbcdao.imp.JDBCTrainDao;
import com.railvayticketiffice.dao.jdbcdao.interfaces.CrudGenericDao;
import com.railvayticketiffice.dao.jdbcdao.interfaces.TrainDao;
import com.railvayticketiffice.entity.Station;
import com.railvayticketiffice.enums.DaoType;
import com.railvayticketiffice.exeptions.PersistException;
import com.railvayticketiffice.factory.DaoFactory;
import org.apache.log4j.Logger;

public class TrainService {
    private static final Logger LOG = Logger.getLogger(StationService.class);

    private CrudGenericDao<Station, Integer> flightCrudDao;
    private TrainDao trainDao;

    public TrainService() {
        this.flightCrudDao = DaoFactory.getEntityDao(DaoType.TRAIN);
        this.trainDao = new JDBCTrainDao();
    }



    public String getTrainName(int trainId){
        String name = null;
        try {
            name = trainDao.getTrainName(trainId);
        } catch (PersistException e) {
            LOG.error(e);
        }
        return name;
    }
}
