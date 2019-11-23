package com.railvayticketiffice.dao.jdbcdao.imp;

import com.railvayticketiffice.constant.SqlConstants;
import com.railvayticketiffice.dao.jdbcdao.interfaces.EntityMapper;
import com.railvayticketiffice.entity.Ticket;
import com.railvayticketiffice.exeptions.PersistException;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class JDBCTicketDao extends AbstractJDBCDao<Ticket, Integer> {

    private static final Logger LOG = Logger.getLogger(JDBCTicketDao.class);

    private static final String TABLE_TICKETS = "tickets";

    private static final String COLUMN_FLIGHT_ID = "flight_id";

    private static final String COLUMN_USER_ID = "user_id";

    private static final String COLUMN_COST = "cost";

    private static final String COLUMN_SEAT_ID = "seat_id";

    private static final String COLUMN_STATUS = "status";

    @Override
    public String getSelectQuery() {
        return SqlConstants.SELECT + " " + SqlConstants.ALL + " " + SqlConstants.FROM + " " + TABLE_TICKETS;
    }

    @Override
    public String getCreateQuery() {
        return SqlConstants.INSERT_INTO + " " + TABLE_TICKETS +
                " (" +
                COLUMN_FLIGHT_ID + ", " +
                COLUMN_USER_ID + ", " +
                COLUMN_COST + ", " +
                COLUMN_SEAT_ID + ", " +
                COLUMN_STATUS +
                ") " +
                SqlConstants.VALUES + " (?,?,?,?,?) " +
                "RETURNING " + SqlConstants.ALL;
    }

    @Override
    public String getUpdateQuery() {
        return SqlConstants.UPDATE + " " + TABLE_TICKETS +
                SqlConstants.SET + " " +
                COLUMN_FLIGHT_ID + " = ?, " +
                COLUMN_USER_ID + " = ?, " +
                COLUMN_COST + " = ?, " +
                COLUMN_SEAT_ID + " = ?, " +
                COLUMN_STATUS + " = ? " +
                SqlConstants.WHERE + " " + COLUMN_ID + " = ?";
    }

    @Override
    public String getDeleteQuery() {
        return SqlConstants.DELETE + " " + SqlConstants.FROM + " " + TABLE_TICKETS + " " + SqlConstants.WHERE + " " + COLUMN_ID + " = ?";
    }

    @Override
    public Ticket create() throws PersistException {
        Ticket ticket = new Ticket();
        return persist(ticket);
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Ticket object) throws PersistException {
        try {
            statement.setInt(1, object.getFlightId());
            statement.setInt(2, object.getUserId());
            statement.setDouble(3, object.getCost());
            statement.setInt(4, object.getSeatId());
            statement.setString(5, object.getStatus());
        } catch (SQLException e) {
            PersistException persistException = new PersistException(e);
            LOG.error(persistException);
            throw persistException;
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Ticket object) throws PersistException {
        try {
            if (object.getFlightId() != 0) {
                statement.setInt(1, object.getFlightId());
            } else {
                statement.setNull(1, Types.INTEGER);
            }
            if (object.getUserId() != 0) {
                statement.setInt(2, object.getUserId());
            } else {
                statement.setNull(2, Types.INTEGER);
            }
            if (object.getCost() != 0) {
                statement.setDouble(3, object.getCost());
            } else {
                statement.setNull(3, Types.DOUBLE);
            }
            if (object.getSeatId() != 0) {
                statement.setDouble(4, object.getSeatId());
            } else {
                statement.setNull(4, Types.INTEGER);
            }
            if (object.getStatus() != null) {
                statement.setString(5, object.getStatus());
            } else {
                statement.setNull(5, Types.NVARCHAR);
            }
            statement.setInt(6, object.getId());
        } catch (SQLException e) {
            PersistException persistException = new PersistException(e);
            LOG.error(persistException);
            throw persistException;
        }
    }

    @Override
    protected EntityMapper<Ticket> getMapper() {
        return resultSet -> new Ticket(
                resultSet.getInt(COLUMN_ID),
                resultSet.getInt(COLUMN_FLIGHT_ID),
                resultSet.getInt(COLUMN_USER_ID),
                resultSet.getDouble(COLUMN_COST),
                resultSet.getInt(COLUMN_SEAT_ID),
                resultSet.getString(COLUMN_STATUS)
        );
    }
}
