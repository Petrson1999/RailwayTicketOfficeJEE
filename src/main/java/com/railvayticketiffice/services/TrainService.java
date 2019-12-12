package com.railvayticketiffice.services;

import com.railvayticketiffice.dao.jdbcdao.imp.JDBCTrainDao;
import com.railvayticketiffice.dao.jdbcdao.interfaces.CrudGenericDao;
import com.railvayticketiffice.dao.jdbcdao.interfaces.TrainDao;
import com.railvayticketiffice.entity.Train;
import com.railvayticketiffice.enums.DaoType;
import com.railvayticketiffice.exeptions.PersistException;
import com.railvayticketiffice.factory.DaoFactory;
import com.railvayticketiffice.web.form.request.AddTrainForm;
import org.apache.log4j.Logger;

import java.util.List;

public class TrainService {
    private static final Logger LOG = Logger.getLogger(StationService.class);

    private CrudGenericDao<Train, Integer> trainCrudDao;
    private TrainDao trainDao;

    public TrainService() {
        this.trainCrudDao = DaoFactory.getEntityDao(DaoType.TRAIN);
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

    public List<Train> getAllTrains(){
        List<Train> trains = null;
        try {
            trains = trainCrudDao.getAll();
        } catch (PersistException e) {
            LOG.error(e);
        }
        return trains;
    }

    public boolean addTrain(AddTrainForm addTrainForm){
        Train train = new Train(addTrainForm.getTrainName());
        try {
            this.trainCrudDao.persist(train);
            return true;
        } catch (PersistException e) {
            LOG.error(e);
            return false;
        }
    }
}
