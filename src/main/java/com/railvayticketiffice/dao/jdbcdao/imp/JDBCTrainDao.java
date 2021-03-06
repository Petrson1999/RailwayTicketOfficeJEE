package com.railvayticketiffice.dao.jdbcdao.imp;


import com.railvayticketiffice.constant.SqlConstants;
import com.railvayticketiffice.dao.jdbcdao.interfaces.EntityMapper;
import com.railvayticketiffice.dao.jdbcdao.interfaces.TrainDao;
import com.railvayticketiffice.entity.Train;
import com.railvayticketiffice.exeptions.PersistException;
import com.railvayticketiffice.persistance.DataSourceFactory;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.sql.Types.*;

public class JDBCTrainDao extends AbstractJDBCDao<Train, Integer> implements TrainDao {

    private static final Logger LOG = Logger.getLogger(JDBCTrainDao.class);

    private static final String TABLE_TRAINS = "trains";

    private static final String COLUMN_NAME = "name";

    private static final String COLUMN_LOCOMOTIVE_ID = "locomotive_id";

    @Override
    public String getSelectQuery() {
        return SqlConstants.SELECT + " " + SqlConstants.ALL + " " + SqlConstants.FROM + " " + TABLE_TRAINS;
    }

    @Override
    public String getCreateQuery() {
        return SqlConstants.INSERT_INTO + " " + TABLE_TRAINS + " (" + COLUMN_NAME  + ") " + SqlConstants.VALUES + " (?) " +
                "RETURNING " + SqlConstants.ALL;
    }

    @Override
    public String getUpdateQuery() {
        return SqlConstants.UPDATE + " " + TABLE_TRAINS + " " + SqlConstants.SET + " " + COLUMN_NAME + " = ?, " + COLUMN_LOCOMOTIVE_ID + " = ? " +
                SqlConstants.WHERE + " " + COLUMN_ID + " = ?";
    }

    @Override
    public String getDeleteQuery() {
        return SqlConstants.DELETE + " " + SqlConstants.FROM + " " + TABLE_TRAINS + " " + SqlConstants.WHERE + " " + COLUMN_ID + "= ?";
    }

    @Override
    public Train create() throws PersistException {
        Train train = new Train();
        return persist(train);
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Train object) throws PersistException {
        try {
            statement.setString(1, object.getName());
        } catch (SQLException e) {
            PersistException persistException = new PersistException(e);
            LOG.error(persistException);
            throw persistException;
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Train object) throws PersistException {
        try {
            if (object.getName() != null) {
                statement.setString(1, object.getName());
            } else {
                statement.setNull(1, NVARCHAR);
            }
            if (object.getLocomotiveId() != 0) {
                statement.setInt(2, object.getLocomotiveId());
            } else {
                statement.setNull(2, INTEGER);
            }
            statement.setInt(3, object.getId());
        } catch (SQLException e) {
            PersistException persistException = new PersistException(e);
            LOG.error(persistException);
            throw persistException;
        }
    }

    @Override
    protected EntityMapper<Train> getMapper() {
        return resultSet -> new Train(
                resultSet.getInt(COLUMN_ID),
                resultSet.getString(COLUMN_NAME),
                resultSet.getInt(COLUMN_LOCOMOTIVE_ID)
        );
    }

    @Override
    public String getTrainName(int trainId) throws PersistException {
        final String sql = SqlConstants.SELECT + " " + COLUMN_NAME + " " + SqlConstants.FROM + " " + TABLE_TRAINS +
                " " + SqlConstants.WHERE + " " + COLUMN_ID + "= ?";
        String name = null;
        try (Connection connection = DataSourceFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, trainId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                name = rs.getString(COLUMN_NAME);
            }
        } catch (SQLException e) {
            PersistException persistException = new PersistException(e);
            LOG.error(persistException);
            throw persistException;
        }
        return name;
    }
}
