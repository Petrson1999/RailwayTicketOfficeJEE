package com.railvayticketiffice.dao.jdbcdao.imp;


import com.railvayticketiffice.constant.SqlConstants;
import com.railvayticketiffice.dao.jdbcdao.interfaces.EntityMapper;
import com.railvayticketiffice.entity.Wagon;
import com.railvayticketiffice.exeptions.PersistException;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.sql.Types.INTEGER;
import static java.sql.Types.NVARCHAR;


public class JDBCWagonDao extends AbstractJDBCDao<Wagon, Integer> {

    private static final Logger LOG = Logger.getLogger(JDBCWagonDao.class);

    private static final String TABLE_WAGONS = "wagons";

    private static final String COLUMN_TRAIN_ID = "train_id";

    private static final String COLUMN_TYPE_ID = "type_id";

    private static final String COLUMN_NAME = "type_id";

    @Override
    public String getSelectQuery() {
        return SqlConstants.SELECT + " " + SqlConstants.ALL + " " + SqlConstants.FROM + " " + TABLE_WAGONS;
    }

    @Override
    public String getCreateQuery() {
        return SqlConstants.INSERT_INTO + " " + TABLE_WAGONS +
                " (" +
                COLUMN_TRAIN_ID + ", " +
                COLUMN_TYPE_ID + ", " +
                COLUMN_NAME +
                ") " +
                SqlConstants.VALUES + " (?,?,?) " +
                "RETURNING " + SqlConstants.ALL;
    }

    @Override
    public String getUpdateQuery() {
        return SqlConstants.UPDATE + " " + TABLE_WAGONS +
                SqlConstants.SET + " " +
                COLUMN_TRAIN_ID + " = ?, " +
                COLUMN_TYPE_ID + " = ?, " +
                COLUMN_NAME + " = ? " +
                SqlConstants.WHERE + " " + COLUMN_ID + " = ?";
    }

    @Override
    public String getDeleteQuery() {
        return SqlConstants.DELETE + " " + SqlConstants.FROM + " " + TABLE_WAGONS + " " + SqlConstants.WHERE + " " + COLUMN_ID + " = ?";
    }

    @Override
    public Wagon create() throws PersistException {
        Wagon wagon = new Wagon();
        return persist(wagon);
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Wagon object) throws PersistException {
        try {
            statement.setInt(1, object.getTrainId());
            statement.setInt(2, object.getWagonTypeId());
            statement.setString(3, object.getName());
        } catch (SQLException e) {
            PersistException persistException = new PersistException(e);
            LOG.error(persistException);
            throw persistException;
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Wagon object) throws PersistException {
        try {
            if (object.getTrainId() != 0) {
                statement.setInt(1, object.getTrainId());
            } else {
                statement.setNull(1, INTEGER);
            }
            if (object.getWagonTypeId() != 0) {
                statement.setInt(2, object.getWagonTypeId());
            } else {
                statement.setNull(2, INTEGER);
            }
            if (object.getName() != null) {
                statement.setString(3, object.getName());
            } else {
                statement.setNull(3, NVARCHAR);
            }
        } catch (SQLException e) {
            PersistException persistException = new PersistException(e);
            LOG.error(persistException);
            throw persistException;
        }
    }

    @Override
    protected EntityMapper<Wagon> getMapper() {
        return resultSet -> new Wagon(
                resultSet.getInt(COLUMN_ID),
                resultSet.getInt(COLUMN_TRAIN_ID),
                resultSet.getInt(COLUMN_TYPE_ID),
                resultSet.getString(COLUMN_NAME));
    }
}
