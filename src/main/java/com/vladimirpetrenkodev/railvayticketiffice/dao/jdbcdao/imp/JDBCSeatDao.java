package com.vladimirpetrenkodev.railvayticketiffice.dao.jdbcdao.imp;

import com.vladimirpetrenkodev.railvayticketiffice.dao.jdbcdao.interfasec.EntityMapper;
import com.vladimirpetrenkodev.railvayticketiffice.entity.Seat;
import com.vladimirpetrenkodev.railvayticketiffice.exeptions.PersistException;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import static com.vladimirpetrenkodev.railvayticketiffice.constant.SqlConstants.*;


public class JDBCSeatDao extends AbstractJDBCDao<Seat, Integer> {

    private static final Logger LOG = Logger.getLogger(JDBCSeatDao.class);

    private static final String TABLE_SEATS = "seats";

    private static final String COLUMN_WAGON_ID = "wagon_id";

    private static final String COLUMN_PLACE_NUMBER = "place_number";

    @Override
    public String getSelectQuery() {
        return SELECT + " " + ALL + " " + FROM + " " + TABLE_SEATS;
    }

    @Override
    public String getCreateQuery() {
        return INSERT_INTO + " " + TABLE_SEATS + " (" + COLUMN_WAGON_ID + ", " + COLUMN_PLACE_NUMBER + ") " +
                VALUES + " (?,?) " + "RETURNING " + ALL;
    }

    @Override
    public String getUpdateQuery() {
        return UPDATE + " " + TABLE_SEATS + " " + SET + " " + COLUMN_WAGON_ID + " = ?, " + COLUMN_PLACE_NUMBER + " = ? " +
                WHERE + " " + COLUMN_ID + " = ?";
    }

    @Override
    public String getDeleteQuery() {
        return DELETE + " " + FROM + " " + TABLE_SEATS + " " + WHERE + " " + COLUMN_ID + "= ?";
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
}
