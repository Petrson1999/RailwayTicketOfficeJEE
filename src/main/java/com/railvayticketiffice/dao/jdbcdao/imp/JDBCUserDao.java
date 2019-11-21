package com.railvayticketiffice.dao.jdbcdao.imp;

import com.railvayticketiffice.constant.SqlConstants;
import com.railvayticketiffice.dao.jdbcdao.interfaces.EntityMapper;
import com.railvayticketiffice.entity.User;
import com.railvayticketiffice.exeptions.PersistException;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class JDBCUserDao extends AbstractJDBCDao<User, Integer> {

    private static final Logger LOG = Logger.getLogger(JDBCUserDao.class);

    private static final String TABLE_USERS = "users";

    private static final String COLUMN_LOGIN = "login";

    private static final String COLUMN_PASSWORD = "password";

    private static final String COLUMN_ROLE = "role";

    private static final String COLUMN_NAME = "name";

    private static final String COLUMN_SURNAME = "surname";

    @Override
    public String getSelectQuery() {
        return SqlConstants.SELECT + " " + SqlConstants.ALL + " " + SqlConstants.FROM + " " + TABLE_USERS;
    }

    @Override
    public String getCreateQuery() {
        return SqlConstants.INSERT_INTO + " " + TABLE_USERS +
                " (" +
                COLUMN_LOGIN + ", " +
                COLUMN_PASSWORD + ", " +
                COLUMN_ROLE + ", " +
                COLUMN_NAME + ", " +
                COLUMN_SURNAME + " " +
                ") " +
                SqlConstants.VALUES + " (?,?,?,?,?) " +
                "RETURNING " + SqlConstants.ALL;
    }

    @Override
    public String getUpdateQuery() {
        return SqlConstants.UPDATE + " " + TABLE_USERS +
                SqlConstants.SET + " " +
                COLUMN_LOGIN + " = ?, " +
                COLUMN_PASSWORD + " = ?, " +
                COLUMN_ROLE + " = ?, " +
                COLUMN_NAME + " = ?, " +
                COLUMN_SURNAME + " = ? " +
                SqlConstants.WHERE + " " + COLUMN_ID + " = ?";
    }

    @Override
    public String getDeleteQuery() {
        return SqlConstants.DELETE + " " + SqlConstants.FROM + " " + TABLE_USERS + " " + SqlConstants.WHERE + " " + COLUMN_ID + " = ?";
    }

    @Override
    public User create() throws PersistException {
        User passenger = new User();
        return persist(passenger);
    }


    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User object) throws PersistException {
        try {
            statement.setString(1, object.getLogin());
            statement.setString(2, object.getPassword());
            statement.setString(3, object.getRole());
            statement.setString(4, object.getName());
            statement.setString(5, object.getSurname());
        } catch (SQLException e) {
            PersistException persistException = new PersistException(e);
            LOG.error(persistException);
            throw persistException;
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User object) throws PersistException {
        try {
            if (object.getLogin() != null) {
                statement.setString(1, object.getLogin());
            } else {
                statement.setNull(1, Types.NVARCHAR);
            }
            if (object.getPassword() != null) {
                statement.setString(2, object.getPassword());
            } else {
                statement.setNull(2, Types.NVARCHAR);
            }
            if (object.getRole() != null) {
                statement.setString(3, object.getRole());
            } else {
                statement.setNull(3, Types.NVARCHAR);
            }
            if (object.getName() != null) {
                statement.setString(4, object.getName());
            } else {
                statement.setNull(4, Types.NVARCHAR);
            }
            if (object.getSurname() != null) {
                statement.setString(5, object.getSurname());
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
    protected EntityMapper<User> getMapper() {
        return resultSet -> new User(
                resultSet.getInt(COLUMN_ID),
                resultSet.getString(COLUMN_LOGIN),
                resultSet.getString(COLUMN_PASSWORD),
                resultSet.getString(COLUMN_ROLE),
                resultSet.getString(COLUMN_NAME),
                resultSet.getString(COLUMN_SURNAME)
        );
    }

}
