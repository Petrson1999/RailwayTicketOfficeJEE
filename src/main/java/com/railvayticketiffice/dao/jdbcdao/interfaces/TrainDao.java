package com.railvayticketiffice.dao.jdbcdao.interfaces;

import com.railvayticketiffice.exeptions.PersistException;

/**
 * Interface with methods for train data management
 *
 * @author Vladimir Petrenko
 */
public interface TrainDao {
    /**
     * get train name by train id
     *
     * @param trainId train id
     *
     * @return name of train with id trainId
     */
    public String getTrainName(int trainId) throws PersistException;

}
