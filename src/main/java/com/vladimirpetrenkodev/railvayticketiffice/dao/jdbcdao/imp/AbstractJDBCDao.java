package com.vladimirpetrenkodev.railvayticketiffice.dao.jdbcdao.imp;

import com.vladimirpetrenkodev.railvayticketiffice.dao.jdbcdao.interfasec.CrudGenericDao;
import com.vladimirpetrenkodev.railvayticketiffice.dao.jdbcdao.interfasec.EntityMapper;
import com.vladimirpetrenkodev.railvayticketiffice.exeptions.PersistException;
import com.vladimirpetrenkodev.railvayticketiffice.persistance.DataSourceFactory;
import com.vladimirpetrenkodev.railvayticketiffice.entity.Identified;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static com.vladimirpetrenkodev.railvayticketiffice.constant.SqlConstants.WHERE;

public abstract class AbstractJDBCDao<T extends Identified<PK>, PK extends Integer> implements CrudGenericDao<T, PK> {

    private static final Logger LOG = Logger.getLogger(AbstractJDBCDao.class);

    protected static final String COLUMN_ID = "id";

    public abstract String getSelectQuery();

    public abstract String getCreateQuery();

    public abstract String getUpdateQuery();

    public abstract String getDeleteQuery();

    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws PersistException;

    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws PersistException;

    protected abstract EntityMapper<T> getMapper();

    public List<T> parseResultSet(ResultSet resultSet) throws PersistException {
        List<T> result = new LinkedList<T>();
        try {
            while (resultSet.next()) {
                T entity = getMapper().map(resultSet);

                result.add(entity);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    public T persist(T object) throws PersistException {
        T persistInstance;
        String sql = getCreateQuery();
        try (PreparedStatement statement = DataSourceFactory.getPreparedStatement(sql)) {
            prepareStatementForInsert(statement, object);
            ResultSet rs = statement.executeQuery();
            List<T> list = parseResultSet(rs);
            if ((list == null) || (list.size() != 1)) {
                PersistException persistException = new PersistException("Exception on findByPK new persist data");
                LOG.error("Exception on findByPK new persist data.", persistException);
                throw persistException;
            }
            persistInstance = list.iterator().next();
        } catch (SQLException e) {
            PersistException persistException = new PersistException(e);
            LOG.error("persist exception", e);
            throw persistException;
        }
        return persistInstance;
    }

    @Override
    public T getByPK(Integer key) throws PersistException {
        List<T> list;
        String sql = getSelectQuery();
        sql += WHERE + " " + COLUMN_ID + " = ?";
        try (PreparedStatement statement = DataSourceFactory.getPreparedStatement(sql)) {
            statement.setInt(1, key);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (SQLException e) {
            PersistException persistException = new PersistException(e);
            LOG.error(persistException);
            throw persistException;
        }
        if (list == null || list.size() == 0) {
            PersistException persistException = new PersistException("Record with PK = " + key + " not found.");
            LOG.error(persistException);
            throw persistException;
        }
        if (list.size() > 1) {
            PersistException persistException = new PersistException("Received more than one record.");
            LOG.error(persistException);
            throw persistException;
        }
        return list.iterator().next();
    }

    @Override
    public void update(T object) throws PersistException {
        String sql = getUpdateQuery();
        try (PreparedStatement statement = DataSourceFactory.getPreparedStatement(sql)) {
            prepareStatementForUpdate(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                PersistException persistException = new PersistException("On update modify more then 1 record: " + count);
                LOG.error(persistException);
                throw persistException;
            }
        } catch (SQLException e) {
            PersistException persistException = new PersistException(e);
            LOG.error(persistException);
            throw persistException;
        }
    }

    @Override
    public void delete(T object) throws PersistException {
        String sql = getDeleteQuery();
        try (PreparedStatement statement = DataSourceFactory.getPreparedStatement(sql)) {
            statement.setObject(1, object.getId());
            int count = statement.executeUpdate();
            if (count != 1) {
                PersistException persistException = new PersistException("On delete modify more then 1 record: " + count);
                LOG.error(persistException);
                throw persistException;
            }
        } catch (SQLException e) {
            PersistException persistException = new PersistException(e);
            LOG.error(persistException);
            throw persistException;
        }
    }

    @Override
    public List<T> getAll() throws PersistException {
        List<T> list;
        String sql = getSelectQuery();
        try (PreparedStatement statement = DataSourceFactory.getPreparedStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (SQLException e) {
            PersistException persistException = new PersistException(e);
            LOG.error(persistException);
            throw persistException;
        }
        return list;
    }

}