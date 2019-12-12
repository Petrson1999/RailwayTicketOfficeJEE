package com.railvayticketiffice.dao.jdbcdao.interfaces;

import com.railvayticketiffice.exeptions.PersistException;

/**
 * Interface with methods for wagon types data management
 *
 * @author Vladimir Petrenko
 */
public interface WagonTypeDao {
    /**
     * get wagon type name by train id
     *
     * @param wagonId wagon id
     *
     * @return name of wagon with id wagonId
     */
    public String getTypeNameById(int wagonId) throws PersistException;
}
