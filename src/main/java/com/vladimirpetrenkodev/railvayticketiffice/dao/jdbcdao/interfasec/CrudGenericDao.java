package com.vladimirpetrenkodev.railvayticketiffice.dao.jdbcdao.interfasec;

import com.vladimirpetrenkodev.railvayticketiffice.exeptions.PersistException;
import com.vladimirpetrenkodev.railvayticketiffice.entity.Identified;

import java.io.Serializable;
import java.util.List;

public interface CrudGenericDao<T extends Identified<PK>, PK extends Serializable> {

    public T create() throws PersistException;

    public T persist(T object) throws PersistException;

    public T getByPK(PK key) throws PersistException;

    public void update(T object) throws PersistException;

    public void delete(T object) throws PersistException;

    public List<T> getAll() throws PersistException;
}
