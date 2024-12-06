package com.chathumal.smapp.dao;

import com.chathumal.smapp.entity.SuperEntity;

import java.io.Serializable;
import java.util.List;

public interface CrudDAO<T extends SuperEntity,ID extends Serializable> extends SuperDAO{
    void add(T t) throws Exception;
    void delete(ID id) throws Exception;
    void update(T t) throws Exception;
    List<T> getAll() throws Exception;
    T search(ID id) throws Exception;

}
