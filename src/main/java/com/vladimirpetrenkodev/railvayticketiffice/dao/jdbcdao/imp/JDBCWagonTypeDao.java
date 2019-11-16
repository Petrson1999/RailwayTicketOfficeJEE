package com.vladimirpetrenkodev.railvayticketiffice.dao.jdbcdao.imp;

import com.vladimirpetrenkodev.railvayticketiffice.dao.jdbcdao.interfasec.EntityMapper;
import com.vladimirpetrenkodev.railvayticketiffice.entity.WagonType;
import com.vladimirpetrenkodev.railvayticketiffice.exeptions.PersistException;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.vladimirpetrenkodev.railvayticketiffice.constant.SqlConstants.*;

public class JDBCWagonTypeDao extends AbstractJDBCDao<WagonType, Integer> {

    private static final Logger LOG = Logger.getLogger(JDBCFlightDao.class);

    private static final String TABLE_WAGON_TYPES = "wagon_types";

    private static final String COLUMN_NUMBER_OF_SEATS = "number_of_seats";

    private static final String COLUMN_COMFORT = "comfort";

    private static final String COLUMN_NAME = "name";

    @Override
    public String getSelectQuery() {
        return SELECT + " " + ALL + " " + FROM + " " + TABLE_WAGON_TYPES;
    }

    @Override
    public String getCreateQuery() {
        return INSERT_INTO + " " + TABLE_WAGON_TYPES +
                " (" +
                COLUMN_NUMBER_OF_SEATS + ", " +
                COLUMN_COMFORT + ", " +
                COLUMN_NAME + ", " +
                ") " +
                VALUES + " (?,?,?) " +
                "RETURNING " + ALL;
    }

    @Override
    public String getUpdateQuery() {
        return UPDATE + " " + TABLE_WAGON_TYPES +
                SET + " " +
                COLUMN_NUMBER_OF_SEATS + " = ?, " +
                COLUMN_COMFORT + " = ?, " +
                COLUMN_NAME + " = ? " +
                WHERE + " " + COLUMN_ID + " = ?";
    }

    @Override
    public String getDeleteQuery() {
        return DELETE + " " + FROM + " " + TABLE_WAGON_TYPES + " " + WHERE + " " + COLUMN_ID + " = ?";
    }

    @Override
    public WagonType create() throws PersistException {
        WagonType wagonType = new WagonType();
        return persist(wagonType);
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, WagonType object) throws PersistException {
        try {
            statement.setInt(1, object.getNumberOfSeats());
            statement.setInt(2, object.getComfort());
            statement.setString(3, object.getName());
        } catch (SQLException e) {
            PersistException persistException = new PersistException(e);
            LOG.error(persistException);
            throw persistException;
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, WagonType object) throws PersistException {
        try {
            if (object.getNumberOfSeats() != 0) {
                statement.setInt(1, object.getNumberOfSeats());
            }
            if (object.getComfort() != 0) {
                statement.setInt(2, object.getComfort());
            }
            if (object.getName() != null) {
                statement.setString(3, object.getName());
            }
        } catch (SQLException e) {
            PersistException persistException = new PersistException(e);
            LOG.error(persistException);
            throw persistException;
        }
    }

    @Override
    protected EntityMapper<WagonType> getMapper() {
        return resultSet -> new WagonType(
                resultSet.getInt(COLUMN_ID),
                resultSet.getInt(COLUMN_NUMBER_OF_SEATS),
                resultSet.getInt(COLUMN_COMFORT),
                resultSet.getString(COLUMN_NAME)
        );
    }
}
