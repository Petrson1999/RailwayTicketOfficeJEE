package com.railvayticketiffice.dao.jdbcdao.interfaces;

import com.railvayticketiffice.exeptions.PersistException;

public interface WagonTypeDao {
    public String getTypeNameById(int wagonId) throws PersistException;
}
