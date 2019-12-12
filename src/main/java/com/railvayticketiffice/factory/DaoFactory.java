package com.railvayticketiffice.factory;

import com.railvayticketiffice.dao.jdbcdao.imp.*;
import com.railvayticketiffice.dao.jdbcdao.interfaces.CrudGenericDao;
import com.railvayticketiffice.enums.DaoType;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory for dao
 */
public class DaoFactory {

    private static Map<DaoType, CrudGenericDao> daoMap = new HashMap<>();

    static {
        daoMap.put(DaoType.USER, new JDBCUserDao());
        daoMap.put(DaoType.FLIGHT, new JDBCFlightDao());
        daoMap.put(DaoType.STATION, new JDBCStationDao());
        daoMap.put(DaoType.TRAIN, new JDBCTrainDao());
        daoMap.put(DaoType.WAGON, new JDBCWagonDao());
        daoMap.put(DaoType.SEAT, new JDBCSeatDao());
        daoMap.put(DaoType.TICKET, new JDBCTicketDao());
        daoMap.put(DaoType.WAGON_TYPE, new JDBCWagonTypeDao());
    }

    private DaoFactory() {
    }

    public static CrudGenericDao getEntityDao(DaoType daoType){
        CrudGenericDao entityDao = daoMap.get(daoType);
        if(entityDao != null ){
            return entityDao;
        }
        throw new RuntimeException("Dao with current dao type do not exist: " + daoType.name());
    }
}
