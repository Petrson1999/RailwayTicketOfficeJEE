package com.railvayticketiffice.dao.jdbcdao.imp;


import com.railvayticketiffice.constant.SqlConstants;
import com.railvayticketiffice.dao.jdbcdao.interfaces.EntityMapper;
import com.railvayticketiffice.entity.Train;
import com.railvayticketiffice.exeptions.PersistException;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.sql.Types.*;

public class JDBCTrainDao extends AbstractJDBCDao<Train, Integer> {

    private static final Logger LOG = Logger.getLogger(JDBCTrainDao.class);

    private static final String TABLE_TRAINS = "trains";

    private static final String COLUMN_NAME = "ticket_id";

    private static final String COLUMN_LOCOMOTIVE_ID = "locomotive_id";

    @Override
    public String getSelectQuery() {
        return SqlConstants.SELECT + " " + SqlConstants.ALL + " " + SqlConstants.FROM + " " + TABLE_TRAINS;
    }

    @Override
    public String getCreateQuery() {
        return SqlConstants.INSERT_INTO + " " + TABLE_TRAINS + " (" + COLUMN_NAME + ", " + COLUMN_LOCOMOTIVE_ID + ") " + SqlConstants.VALUES + " (?,?) " +
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
            statement.setInt(1, object.getLocomotiveId());
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
}
