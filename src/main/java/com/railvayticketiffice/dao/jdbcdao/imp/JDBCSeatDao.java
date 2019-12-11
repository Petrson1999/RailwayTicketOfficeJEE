package com.railvayticketiffice.dao.jdbcdao.imp;

import com.railvayticketiffice.constant.SqlConstants;
import com.railvayticketiffice.dao.jdbcdao.interfaces.EntityMapper;
import com.railvayticketiffice.dao.jdbcdao.interfaces.SeatDao;
import com.railvayticketiffice.entity.Seat;
import com.railvayticketiffice.exeptions.PersistException;
import com.railvayticketiffice.persistance.DataSourceFactory;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

import static com.railvayticketiffice.constant.SqlConstants.ALL;


public class JDBCSeatDao extends AbstractJDBCDao<Seat, Integer> implements SeatDao {

    private static final Logger LOG = Logger.getLogger(JDBCSeatDao.class);

    private static final String TABLE_SEATS = "seats";

    private static final String COLUMN_WAGON_ID = "wagon_id";

    private static final String COLUMN_PLACE_NUMBER = "place_number";

    @Override
    public String getSelectQuery() {
        return SqlConstants.SELECT + " " + ALL + " " + SqlConstants.FROM + " " + TABLE_SEATS;
    }

    @Override
    public String getCreateQuery() {
        return SqlConstants.INSERT_INTO + " " + TABLE_SEATS + " (" + COLUMN_WAGON_ID + ", " + COLUMN_PLACE_NUMBER + ") " +
                SqlConstants.VALUES + " (?,?) " + "RETURNING " + ALL;
    }

    @Override
    public String getUpdateQuery() {
        return SqlConstants.UPDATE + " " + TABLE_SEATS + " " + SqlConstants.SET + " " + COLUMN_WAGON_ID + " = ?, " + COLUMN_PLACE_NUMBER + " = ? " +
                SqlConstants.WHERE + " " + COLUMN_ID + " = ?";
    }

    @Override
    public String getDeleteQuery() {
        return SqlConstants.DELETE + " " + SqlConstants.FROM + " " + TABLE_SEATS + " " + SqlConstants.WHERE + " " + COLUMN_ID + "= ?";
    }

    @Override
    public Seat create() throws PersistException {
        Seat seat = new Seat();
        return persist(seat);
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Seat object) throws PersistException {
        try {
            statement.setInt(1, object.getWagonId());
            statement.setInt(2, object.getPlaceNumber());
        } catch (SQLException e) {
            PersistException persistException = new PersistException(e);
            LOG.error(persistException);
            throw persistException;
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Seat object) throws PersistException {
        try {
            if (object.getWagonId() != 0) {
                statement.setInt(1, object.getWagonId());
            } else {
                statement.setNull(1, Types.NULL);
            }
            if (object.getPlaceNumber() != 0) {
                statement.setInt(2, object.getPlaceNumber());
            }
            statement.setInt(3, object.getId());
        } catch (SQLException e) {
            PersistException persistException = new PersistException(e);
            LOG.error(persistException);
            throw persistException;
        }
    }

    @Override
    protected EntityMapper<Seat> getMapper() {
        return resultSet -> new Seat(
                resultSet.getInt(COLUMN_ID),
                resultSet.getInt(COLUMN_WAGON_ID),
                resultSet.getInt(COLUMN_PLACE_NUMBER)
        );
    }

    @Override
    public List<Seat> getWagonSeats(int wagonId) throws PersistException {
        final String sql = SqlConstants.SELECT + " " + ALL + " " + SqlConstants.FROM + " " + TABLE_SEATS +
                " " + SqlConstants.WHERE + " " + COLUMN_WAGON_ID + "= ?";
        List<Seat> seats;
        try (Connection connection = DataSourceFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, wagonId);
            ResultSet rs = statement.executeQuery();
            seats = parseResultSet(rs);
        } catch (SQLException e) {
            PersistException persistException = new PersistException(e);
            LOG.error(persistException);
            throw persistException;
        }
        return seats;
    }
}
