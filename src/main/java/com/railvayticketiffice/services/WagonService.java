package com.railvayticketiffice.services;

import com.railvayticketiffice.dao.jdbcdao.imp.JDBCWagonDao;
import com.railvayticketiffice.dao.jdbcdao.imp.JDBCWagonTypeDao;
import com.railvayticketiffice.dao.jdbcdao.interfaces.CrudGenericDao;
import com.railvayticketiffice.dao.jdbcdao.interfaces.WagonDao;
import com.railvayticketiffice.dao.jdbcdao.interfaces.WagonTypeDao;
import com.railvayticketiffice.entity.Wagon;
import com.railvayticketiffice.enums.DaoType;
import com.railvayticketiffice.exeptions.PersistException;
import com.railvayticketiffice.factory.DaoFactory;
import org.apache.log4j.Logger;

import java.util.List;

public class WagonService {

    private static final Logger LOG = Logger.getLogger(WagonService.class);

    private CrudGenericDao<Wagon, Integer> wagonCrudDao;
    private WagonDao wagonDao;
    private WagonTypeDao wagonTypeDao;


    public WagonService() {
        this.wagonCrudDao = DaoFactory.getEntityDao(DaoType.WAGON);
        this.wagonDao = new JDBCWagonDao();
        this.wagonTypeDao = new JDBCWagonTypeDao();
    }

    public List<Wagon> getTrainWagons(int trainId) {
        List<Wagon> wagons = null;
        try {
            wagons = wagonDao.getTrainWagons(trainId);
        } catch (PersistException e) {
            LOG.error(e);
        }
        return wagons;
    }

    public String getWagonType(int wagonId){
        String typeName = null;
        try{
            typeName = wagonTypeDao.getTypeNameById(wagonId);
        }
        catch (PersistException e){
            LOG.error(e);
        }
        return typeName;
    }

    public List<Wagon> getAll(){
        List<Wagon> wagons = null;
        try {
            wagons = wagonCrudDao.getAll();
        } catch (PersistException e) {
            LOG.error(e);
        }
        return wagons;
    }
}
