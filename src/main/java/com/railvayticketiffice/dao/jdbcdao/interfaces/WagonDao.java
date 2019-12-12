package com.railvayticketiffice.dao.jdbcdao.interfaces;

import com.railvayticketiffice.entity.Wagon;
import com.railvayticketiffice.exeptions.PersistException;

import java.util.List;

/**
 * Interface with methods for wagon data management
 *
 * @author Vladimir Petrenko
 */
public interface WagonDao {
    /**
     * get train wagons with chosen train id
     *
     * @param trainId train id
     *
     * @return list of wagons with id trainId
     */
    public List<Wagon> getTrainWagons(int trainId) throws PersistException;
}
