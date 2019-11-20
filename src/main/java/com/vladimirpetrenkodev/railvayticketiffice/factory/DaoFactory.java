package com.vladimirpetrenkodev.railvayticketiffice.factory;

import com.vladimirpetrenkodev.railvayticketiffice.dao.jdbcdao.imp.JDBCUserDao;
import com.vladimirpetrenkodev.railvayticketiffice.dao.jdbcdao.interfasec.CrudGenericDao;
import com.vladimirpetrenkodev.railvayticketiffice.enums.DaoType;

import java.util.HashMap;
import java.util.Map;

public class DaoFactory {

    private static Map<DaoType, CrudGenericDao> daoMap = new HashMap<>();

    static {
        daoMap.put(DaoType.USER, new JDBCUserDao());
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
