package com.railvayticketiffice.dao.jdbcdao.imp;

import com.railvayticketiffice.constant.SqlConstants;
import com.railvayticketiffice.dao.jdbcdao.interfaces.EntityMapper;
import com.railvayticketiffice.entity.Flight;
import com.railvayticketiffice.exeptions.PersistException;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class JDBCFlightDao extends AbstractJDBCDao<Flight, Integer> {

    private static final Logger LOG = Logger.getLogger(JDBCFlightDao.class);

    private static final String TABLE_FLIGHTS = "flights";

    private static final String COLUMN_DEPARTURE_STATION_ID = "departure_station_id";

    private static final String COLUMN_ARRIVAL_STATION_ID = "arrival_station_id";

    private static final String COLUMN_DEPARTURE_TIME = "departure_time";

    private static final String COLUMN_ARRIVAL_TIME = "arrival_time";

    private static final String COLUMN_COST = "cost";

    private static final String COLUMN_NAME = "name";

    private static final String COLUMN_TRAIN_ID = "train_id";

    @Override
    public String getSelectQuery() {
        return SqlConstants.SELECT + " " + SqlConstants.ALL + " " + SqlConstants.FROM + " " + TABLE_FLIGHTS;
    }

    @Override
    public String getCreateQuery() {
        return SqlConstants.INSERT_INTO + " " + TABLE_FLIGHTS +
                " (" +
                COLUMN_DEPARTURE_STATION_ID + ", " +
                COLUMN_ARRIVAL_STATION_ID + ", " +
                COLUMN_DEPARTURE_TIME + ", " +
                COLUMN_ARRIVAL_TIME + ", " +
                COLUMN_COST + ", " +
                COLUMN_NAME + ", " +
                COLUMN_TRAIN_ID +
                ") " +
                SqlConstants.VALUES + " (?,?,?,?,?,?,?) " +
                "RETURNING " + SqlConstants.ALL;
    }

    @Override
    public String getUpdateQuery() {
        return SqlConstants.UPDATE + " " + TABLE_FLIGHTS +
                SqlConstants.SET + " " +
                COLUMN_DEPARTURE_STATION_ID + " = ?, " +
                COLUMN_ARRIVAL_STATION_ID + " = ?, " +
                COLUMN_DEPARTURE_TIME + " = ?, " +
                COLUMN_ARRIVAL_TIME + " = ?, " +
                COLUMN_COST + " = ?, " +
                COLUMN_NAME + " = ?, " +
                COLUMN_TRAIN_ID + " = ? " +
                SqlConstants.WHERE + " " + COLUMN_ID + " = ?";
    }

    @Override
    public String getDeleteQuery() {
        return SqlConstants.DELETE + " " + SqlConstants.FROM + " " + TABLE_FLIGHTS + " " + SqlConstants.WHERE + " " + COLUMN_ID + " = ?";
    }

    @Override
    public Flight create() throws PersistException {
        Flight flight = new Flight();
        return persist(flight);
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Flight object) throws PersistException {
        try {
            statement.setInt(1, object.getDepartureStationId());
            statement.setInt(2, object.getArrivalStationId());
            statement.setDate(3, java.sql.Date.valueOf( object.getDepartureTime().toLocalDate()));
            statement.setDate(4,  java.sql.Date.valueOf(object.getArrivalTime().toLocalDate()));
            statement.setDouble(5, object.getCost());
            statement.setString(6, object.getName());
            statement.setInt(7, object.getTrainId());
        } catch (SQLException e) {
            PersistException persistException = new PersistException(e);
            LOG.error(persistException);
            throw persistException;
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Flight object) throws PersistException {
        try {
            if (object.getDepartureStationId() != 0) {
                statement.setInt(1, object.getDepartureStationId());
            } else {
                statement.setNull(1, Types.INTEGER);
            }
            if (object.getArrivalStationId() != 0) {
                statement.setInt(2, object.getArrivalStationId());
            } else {
                statement.setNull(2, Types.INTEGER);
            }
            if (object.getDepartureTime() != null) {
                statement.setDate(3, java.sql.Date.valueOf( object.getDepartureTime().toLocalDate()));
            } else {
                statement.setNull(3, Types.DATE);
            }
            if (object.getArrivalTime() != null) {
                statement.setDate(4, java.sql.Date.valueOf(object.getArrivalTime().toLocalDate()));
            } else {
                statement.setNull(4, Types.DATE);
            }
            if (object.getCost() != 0) {
                statement.setDouble(5, object.getCost());
            } else {
                statement.setNull(5, Types.DOUBLE);
            }
            if (object.getName() != null) {
                statement.setString(6, object.getName());
            } else {
                statement.setNull(6, Types.VARCHAR);
            }
            if (object.getTrainId() != 0) {
                statement.setInt(7, object.getId());
            } else {
                statement.setNull(7, Types.INTEGER);
            }
            statement.setInt(8, object.getId());
        } catch (SQLException e) {
            PersistException persistException = new PersistException(e);
            LOG.error(persistException);
            throw persistException;
        }
    }

    @Override
    protected EntityMapper<Flight> getMapper() {
        return resultSet -> new Flight(
                resultSet.getInt(COLUMN_ID),
                resultSet.getInt(COLUMN_DEPARTURE_STATION_ID),
                resultSet.getInt(COLUMN_ARRIVAL_STATION_ID),
                resultSet.getTimestamp(COLUMN_DEPARTURE_TIME).toLocalDateTime(),
                resultSet.getTimestamp(COLUMN_ARRIVAL_TIME).toLocalDateTime(),
                resultSet.getDouble(COLUMN_COST),
                resultSet.getString(COLUMN_NAME),
                resultSet.getInt(COLUMN_TRAIN_ID)
        );
    }
}
