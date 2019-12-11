package com.railvayticketiffice.dao.jdbcdao.imp;

import com.railvayticketiffice.constant.SqlConstants;
import com.railvayticketiffice.dao.jdbcdao.interfaces.EntityMapper;
import com.railvayticketiffice.dao.jdbcdao.interfaces.TicketsDao;
import com.railvayticketiffice.entity.Ticket;
import com.railvayticketiffice.entity.User;
import com.railvayticketiffice.exeptions.PersistException;
import com.railvayticketiffice.persistance.DataSourceFactory;
import org.apache.log4j.Logger;

import java.sql.*;
import java.text.ParseException;
import java.util.List;

import static com.railvayticketiffice.constant.SqlConstants.ALL;
import static com.railvayticketiffice.dao.jdbcdao.imp.JDBCUserDao.COLUMN_FUNDS;
import static com.railvayticketiffice.dao.jdbcdao.imp.JDBCUserDao.TABLE_USERS;

public class JDBCTicketDao extends AbstractJDBCDao<Ticket, Integer> implements TicketsDao {

    private static final Logger LOG = Logger.getLogger(JDBCTicketDao.class);

    private static final String TABLE_TICKETS = "tickets";

    private static final String COLUMN_FLIGHT_ID = "flight_id";

    private static final String COLUMN_USER_ID = "user_id";

    private static final String COLUMN_COST = "cost";

    private static final String COLUMN_SEAT_ID = "seat_id";

    @Override
    public String getSelectQuery() {
        return SqlConstants.SELECT + " " + ALL + " " + SqlConstants.FROM + " " + TABLE_TICKETS;
    }

    @Override
    public String getCreateQuery() {
        return SqlConstants.INSERT_INTO + " " + TABLE_TICKETS +
                " (" +
                COLUMN_FLIGHT_ID + ", " +
                COLUMN_USER_ID + ", " +
                COLUMN_COST + ", " +
                COLUMN_SEAT_ID +
                ") " +
                SqlConstants.VALUES + " (?,?,?,?) ";
    }

    @Override
    public String getUpdateQuery() {
        return SqlConstants.UPDATE + " " + TABLE_TICKETS +
                SqlConstants.SET + " " +
                COLUMN_FLIGHT_ID + " = ?, " +
                COLUMN_USER_ID + " = ?, " +
                COLUMN_COST + " = ?, " +
                COLUMN_SEAT_ID + " = ?, " +
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
            statement.setInt(5, object.getId());
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
                resultSet.getInt(COLUMN_SEAT_ID)
        );
    }

    @Override
    public List<Ticket> getFlightTickets(int flightId) throws PersistException {
        final String sql = SqlConstants.SELECT + " " + ALL + " " + SqlConstants.FROM + " " + TABLE_TICKETS +
                " " + SqlConstants.WHERE + " " + COLUMN_FLIGHT_ID + "= ?";
        List<Ticket> tickets;

        try (Connection connection = DataSourceFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, flightId);
            ResultSet rs = statement.executeQuery();
            tickets = parseResultSet(rs);
        } catch (SQLException e) {
            PersistException persistException = new PersistException(e);
            LOG.error(persistException);
            throw persistException;
        }
        return tickets;
    }

    @Override
    public boolean ticketsTransaction(Ticket ticket, User user) throws PersistException {
        if (ticket == null) {
            return false;
        }
        final String addTicket = getCreateQuery();
        final String withdrawMoney = SqlConstants.UPDATE + " " + TABLE_USERS + " " +
                SqlConstants.SET + " " +
                COLUMN_FUNDS + " = ? " +
                SqlConstants.WHERE + " " + COLUMN_ID + " = ?";
        Connection connection = DataSourceFactory.getConnection();
        Savepoint savepointOne = null;
        try {
            connection.setAutoCommit(false);
            savepointOne = connection.setSavepoint("beforePayment");
        } catch (SQLException e) {
            LOG.error(e);
        }
        try (PreparedStatement statementTicket = connection.prepareStatement(addTicket);
             PreparedStatement statementWithdrawMoney = connection.prepareStatement(withdrawMoney)) {


            prepareStatementForInsert(statementTicket, ticket);

            statementWithdrawMoney.setDouble(1, user.getFunds() - ticket.getCost());
            statementWithdrawMoney.setInt(2, user.getId());

            statementTicket.executeUpdate();
            statementWithdrawMoney.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException e) {
            try {
                connection.rollback(savepointOne);
            } catch (SQLException e1) {
                LOG.error(e1);
            }
            PersistException persistException = new PersistException(e);
            LOG.error(persistException);
            throw persistException;
        }
    }
}
