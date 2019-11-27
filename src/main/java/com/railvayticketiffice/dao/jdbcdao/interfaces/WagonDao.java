package com.railvayticketiffice.dao.jdbcdao.interfaces;

import com.railvayticketiffice.entity.Wagon;
import com.railvayticketiffice.exeptions.PersistException;

import java.util.List;

public interface WagonDao {

    public List<Wagon> getTrainWagons(int trainId) throws PersistException;
}
