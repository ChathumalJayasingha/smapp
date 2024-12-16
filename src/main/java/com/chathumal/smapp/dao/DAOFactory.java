package com.chathumal.smapp.dao;

import com.chathumal.smapp.dao.custom.impl.ContentDAOImpl;
import com.chathumal.smapp.dao.custom.impl.UserDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum Type {
        USER,CONTENT

    }

    public SuperDAO getDAO(Type type) {
        switch (type) {
            case USER:
                return new UserDAOImpl();
            case CONTENT:
                return new ContentDAOImpl();
            default:
                return null;
        }
    }


}
