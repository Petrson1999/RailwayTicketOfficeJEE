package com.railvayticketiffice.dao.jdbcdao.imp;

import com.railvayticketiffice.constant.SqlConstants;
import com.railvayticketiffice.dao.jdbcdao.interfaces.EntityMapper;
import com.railvayticketiffice.dao.jdbcdao.interfaces.WagonTypeDao;
import com.railvayticketiffice.entity.WagonType;
import com.railvayticketiffice.exeptions.PersistException;
import com.railvayticketiffice.persistance.DataSourceFactory;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCWagonTypeDao extends AbstractJDBCDao<WagonType, Integer> implements WagonTypeDao {

    private static final Logger LOG = Logger.getLogger(JDBCFlightDao.class);

    private static final String TABLE_WAGON_TYPES = "wagon_types";

    private static final String COLUMN_NUMBER_OF_SEATS = "number_of_seats";

    private static final String COLUMN_COMFORT = "comfort";

    private static final String COLUMN_NAME = "name";

    @Override
    public String getSelectQuery() {
        return SqlConstants.SELECT + " " + SqlConstants.ALL + " " + SqlConstants.FROM + " " + TABLE_WAGON_TYPES;
    }

    @Override
    public String getCreateQuery() {
        return SqlConstants.INSERT_INTO + " " + TABLE_WAGON_TYPES +
                " (" +
                COLUMN_NUMBER_OF_SEATS + ", " +
                COLUMN_COMFORT + ", " +
                COLUMN_NAME + " " +
                ") " +
                SqlConstants.VALUES + " (?,?,?) " +
                "RETURNING " + SqlConstants.ALL;
    }

    @Override
    public String getUpdateQuery() {
        return SqlConstants.UPDATE + " " + TABLE_WAGON_TYPES +
                SqlConstants.SET + " " +
                COLUMN_NUMBER_OF_SEATS + " = ?, " +
                COLUMN_COMFORT + " = ?, " +
                COLUMN_NAME + " = ? " +
                SqlConstants.WHERE + " " + COLUMN_ID + " = ?";
    }

    @Override
    public String getDeleteQuery() {
        return SqlConstants.DELETE + " " + SqlConstants.FROM + " " + TABLE_WAGON_TYPES + " " + SqlConstants.WHERE + " " + COLUMN_ID + " = ?";
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

    @Override
    public String getTypeNameById(int wagonId) throws PersistException{
        final String sql = SqlConstants.SELECT + " " + COLUMN_NAME + " " + SqlConstants.FROM + " " + TABLE_WAGON_TYPES +
                " " + SqlConstants.WHERE + " " + COLUMN_ID + "= ?";
        String typeName = null;

        try (Connection connection = DataSourceFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, wagonId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                typeName = rs.getString(COLUMN_NAME);
            }
        } catch (SQLException e) {
            PersistException persistException = new PersistException(e);
            LOG.error(persistException);
            throw persistException;
        }
        return typeName;
    }
}
