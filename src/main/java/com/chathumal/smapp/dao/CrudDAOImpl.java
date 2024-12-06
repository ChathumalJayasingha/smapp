package com.chathumal.smapp.dao;

import com.chathumal.smapp.entity.SuperEntity;
import org.hibernate.Session;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class CrudDAOImpl <T extends SuperEntity,ID extends Serializable> implements CrudDAO<T, ID>{
    protected Session session;
    private Class<T> entity;

    public CrudDAOImpl() {
        entity = (Class<T>)(((ParameterizedType)(this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]);
    }

    @Override
    public void setSession(Session session) {
        this.session=session;
    }
    @Override
    public void add(T t) throws Exception {
        session.save(t);
    }

    @Override
    public void delete(ID id) throws Exception {
        session.delete(session.load(entity,id));
    }

    @Override
    public void update(T t) throws Exception {
        session.update(t);
    }

    @Override
    public List<T> getAll() throws Exception {
        return session.createQuery("FROM " + entity.getName()).list();
    }

    @Override
    public T search(ID id) throws Exception {
        return session.get(entity,id);
    }


}
