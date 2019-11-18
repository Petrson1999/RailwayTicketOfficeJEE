package com.vladimirpetrenkodev.railvayticketiffice.dao.jdbcdao.imp;

import com.vladimirpetrenkodev.railvayticketiffice.dao.jdbcdao.interfasec.EntityMapper;
import com.vladimirpetrenkodev.railvayticketiffice.entity.Locomotive;
import com.vladimirpetrenkodev.railvayticketiffice.exeptions.PersistException;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.vladimirpetrenkodev.railvayticketiffice.constant.SqlConstants.*;


public class JDBCLocomotiveDao extends AbstractJDBCDao<Locomotive, Integer> {

    private static final Logger LOG = Logger.getLogger(JDBCLocomotiveDao.class);

    private static final String TABLE_LOCOMOTIVES = "locomotives";

    private static final String COLUMN_NAME = "name";

    @Override
    public String getSelectQuery() {
        return SELECT + " " + ALL + " " + FROM + " " + TABLE_LOCOMOTIVES;
    }

    @Override
    public String getCreateQuery() {
        return INSERT_INTO + " " + TABLE_LOCOMOTIVES + " (" + COLUMN_NAME + ") " + VALUES + " (?) " +
                "RETURNING " + ALL;
    }

    @Override
    public String getUpdateQuery() {
        return UPDATE + " " + TABLE_LOCOMOTIVES + " " + SET + " " + COLUMN_NAME + " = ?" + " " + WHERE + " " + COLUMN_ID + "= ?";
    }

    @Override
    public String getDeleteQuery() {
        return DELETE + " " + FROM + " " + TABLE_LOCOMOTIVES + " " + WHERE + " " + COLUMN_ID + "= ?";
    }

    @Override
    public Locomotive create() throws PersistException {
        Locomotive locomotive = new Locomotive();
        return persist(locomotive);
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Locomotive object) throws PersistException {
        try {
            statement.setString(1, object.getName());
        } catch (SQLException e) {
            PersistException persistException = new PersistException(e);
            LOG.error(persistException);
            throw persistException;
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Locomotive object) throws PersistException {
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
    protected EntityMapper<Locomotive> getMapper() {
        return resultSet -> new Locomotive(
                resultSet.getInt(COLUMN_ID),
                resultSet.getString(COLUMN_NAME)
        );
    }
}
