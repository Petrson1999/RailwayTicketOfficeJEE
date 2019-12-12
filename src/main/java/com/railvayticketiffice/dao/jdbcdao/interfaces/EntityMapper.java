package com.railvayticketiffice.dao.jdbcdao.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Functional interface for map result set to entity
 *
 * @author Vladimir Petrenko
 */
@FunctionalInterface
public interface EntityMapper<T> {

    /**
     * map result set to entity
     *
     * @return entity from result set
     */
    T map(ResultSet resultSet) throws SQLException;
}
