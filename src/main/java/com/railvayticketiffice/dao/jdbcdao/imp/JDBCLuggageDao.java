package com.railvayticketiffice.dao.jdbcdao.imp;


import com.railvayticketiffice.constant.SqlConstants;
import com.railvayticketiffice.dao.jdbcdao.interfaces.EntityMapper;
import com.railvayticketiffice.entity.Luggage;
import com.railvayticketiffice.exeptions.PersistException;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class JDBCLuggageDao extends AbstractJDBCDao<Luggage, Integer> {

    private static final Logger LOG = Logger.getLogger(JDBCLuggageDao.class);

    private static final String TABLE_LUGGAGE = "luggage";

    private static final String COLUMN_TICKET_ID = "ticket_id";

    @Override
    public String getSelectQuery() {
        return SqlConstants.SELECT + " " + SqlConstants.ALL + " " + SqlConstants.FROM + " " + TABLE_LUGGAGE;
    }

    @Override
    public String getCreateQuery() {
        return SqlConstants.INSERT_INTO + " " + TABLE_LUGGAGE + " (" + COLUMN_TICKET_ID + ") " + SqlConstants.VALUES + " (?) " +
                "RETURNING " + SqlConstants.ALL;
    }

    @Override
    public String getUpdateQuery() {
        return SqlConstants.UPDATE + " " + TABLE_LUGGAGE + " " + SqlConstants.SET + " " + COLUMN_TICKET_ID + " = ?" + " " +
                SqlConstants.WHERE + " " + COLUMN_ID + "= ?";
    }

    @Override
    public String getDeleteQuery() {
        return SqlConstants.DELETE + " " + SqlConstants.FROM + " " + TABLE_LUGGAGE + " " + SqlConstants.WHERE + " " + COLUMN_ID + "= ?";
    }

    @Override
    public Luggage create() throws PersistException {
        Luggage luggage = new Luggage();
        return persist(luggage);
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Luggage object) throws PersistException {
        try {
            statement.setInt(1, Types.INTEGER);
        } catch (SQLException e) {
            PersistException persistException = new PersistException(e);
            LOG.error(persistException);
            throw persistException;
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Luggage object) throws PersistException {
        try {
            if (object.getTicketId() != 0) {
                statement.setInt(1, object.getTicketId());
            }
            statement.setInt(2, object.getId());
        } catch (SQLException e) {
            PersistException persistException = new PersistException(e);
            LOG.error(persistException);
            throw persistException;
        }
    }

    @Override
    protected EntityMapper<Luggage> getMapper() {
        return resultSet -> new Luggage(
                resultSet.getInt(COLUMN_ID),
                resultSet.getInt(COLUMN_TICKET_ID)
        );
    }

}
