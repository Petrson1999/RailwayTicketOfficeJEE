package com.railvayticketiffice.dao.jdbcdao.imp;

import com.railvayticketiffice.constant.SqlConstants;
import com.railvayticketiffice.dao.jdbcdao.interfaces.EntityMapper;
import com.railvayticketiffice.entity.Station;
import com.railvayticketiffice.exeptions.PersistException;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCStationDao extends AbstractJDBCDao<Station, Integer> {

    private static final Logger LOG = Logger.getLogger(JDBCStationDao.class);

    private static final String TABLE_STATIONS = "stations";

    private static final String COLUMN_NAME = "name";

    @Override
    public String getSelectQuery() {
        return SqlConstants.SELECT + " " + SqlConstants.ALL + " " + SqlConstants.FROM + " " + TABLE_STATIONS;
    }

    @Override
    public String getCreateQuery() {
        return SqlConstants.INSERT_INTO + " " + TABLE_STATIONS + " (" + COLUMN_NAME + ") " + SqlConstants.VALUES + " (?) " +
                "RETURNING " + SqlConstants.ALL;
    }

    @Override
    public String getUpdateQuery() {
        return SqlConstants.UPDATE + " " + TABLE_STATIONS + " " + SqlConstants.SET + " " + COLUMN_NAME + " = ?" + " " + SqlConstants.WHERE + " " + COLUMN_ID + "= ?";
    }

    @Override
    public String getDeleteQuery() {
        return SqlConstants.DELETE + " " + SqlConstants.FROM + " " + TABLE_STATIONS + " " + SqlConstants.WHERE + " " + COLUMN_ID + "= ?";
    }

    @Override
    public Station create() throws PersistException {
        Station station = new Station();
        return persist(station);
    }


    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Station object) throws PersistException {
        try {
            statement.setString(1, object.getName());
        } catch (SQLException e) {
            PersistException persistException = new PersistException(e);
            LOG.error(persistException);
            throw persistException;
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Station object) throws PersistException {
        try {
            if (object.getName() != null) {
                statement.setString(1, object.getName());
            }
            statement.setInt(2, object.getId());
        } catch (SQLException e) {
            PersistException persistException = new PersistException(e);
            LOG.error(persistException);
            throw persistException;
        }
    }

    @Override
    protected EntityMapper<Station> getMapper() {
        return resultSet -> new Station(
                resultSet.getInt(COLUMN_ID),
                resultSet.getString(COLUMN_NAME)
        );
    }

}
