package com.railvayticketiffice.dao.jdbcdao.interfaces;

import com.railvayticketiffice.exeptions.PersistException;
import com.railvayticketiffice.entity.Identified;

import java.io.Serializable;
import java.util.List;

/**
 * Interface with CRUD methods
 *
 * @author Vladimir Petrenko
 */
public interface CrudGenericDao<T extends Identified<PK>, PK extends Serializable> {

    /**
     * Create entity in DB with id and empty columns
     *
     * @return created entity
     */
    public T create() throws PersistException;

    /**
     * Save entity in DB
     *
     * @return saved entity
     */
    public T persist(T object) throws PersistException;

    /**
     * Get entity by id
     *
     * @return entity by id
     */
    public T getByPK(PK key) throws PersistException;

    /**
     * Update entity by id
     */
    public void update(T object) throws PersistException;

    /**
     * Delete entity by id
     */
    public void delete(T object) throws PersistException;

    /**
     * Get all entity
     *
     * @return list if entities
     */
    public List<T> getAll() throws PersistException;
}
