package com.railvayticketiffice.dao.jdbcdao.interfaces;

import com.railvayticketiffice.exeptions.PersistException;

public interface TrainDao {
    public String getTrainName(int trainId) throws PersistException;

}
