package com.vladimirpetrenkodev.railvayticketiffice.dao.jdbcdao.imp;


import com.vladimirpetrenkodev.railvayticketiffice.dao.jdbcdao.interfasec.EntityMapper;
import com.vladimirpetrenkodev.railvayticketiffice.entity.Luggage;
import com.vladimirpetrenkodev.railvayticketiffice.exeptions.PersistException;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import static com.vladimirpetrenkodev.railvayticketiffice.constant.SqlConstants.*;

public class JDBCLuggageDao extends AbstractJDBCDao<Luggage, Integer> {

    private static final Logger LOG = Logger.getLogger(JDBCLuggageDao.class);

    private static final String TABLE_LUGGAGE = "luggage";

    private static final String COLUMN_TICKET_ID = "ticket_id";

    @Override
    public String getSelectQuery() {
        return SELECT + " " + ALL + " " + FROM + " " + TABLE_LUGGAGE;
    }

    @Override
    public String getCreateQuery() {
        return INSERT_INTO + " " + TABLE_LUGGAGE + " (" + COLUMN_TICKET_ID + ") " + VALUES + " (?) " +
                "RETURNING " + ALL;
    }

    @Override
    public String getUpdateQuery() {
        return UPDATE + " " + TABLE_LUGGAGE + " " + SET + " " + COLUMN_TICKET_ID + " = ?" + " " +
                WHERE + " " + COLUMN_ID + "= ?";
    }

    @Override
    public String getDeleteQuery() {
        return DELETE + " " + FROM + " " + TABLE_LUGGAGE + " " + WHERE + " " + COLUMN_ID + "= ?";
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
