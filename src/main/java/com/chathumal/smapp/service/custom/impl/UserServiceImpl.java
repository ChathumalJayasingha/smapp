package com.chathumal.smapp.service.custom.impl;

import com.chathumal.smapp.configuration.FactoryConfiguration;
import com.chathumal.smapp.dao.DAOFactory;
import com.chathumal.smapp.dao.custom.UserDAO;
import com.chathumal.smapp.entity.User;
import com.chathumal.smapp.service.custom.UserService;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserServiceImpl implements UserService {
    UserDAO userDAO= (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.Type.USER);

    @Override
    public void addUser(String ID, String password) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        userDAO.setSession(session);
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            userDAO.add(new User(1,"a","HI","075685"));
            transaction.commit();
        } catch (Throwable t) {
            transaction.rollback();
            t.printStackTrace();
        } finally {
            session.close();
        }
    }
}
