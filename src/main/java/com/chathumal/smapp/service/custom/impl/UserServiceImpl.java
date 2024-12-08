package com.chathumal.smapp.service.custom.impl;

import com.chathumal.smapp.configuration.FactoryConfiguration;
import com.chathumal.smapp.dao.DAOFactory;
import com.chathumal.smapp.dao.custom.UserDAO;
import com.chathumal.smapp.entity.User;
import com.chathumal.smapp.service.custom.UserService;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserServiceImpl implements UserService {

    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.Type.USER);

    @Override
    public void addUser(String name, String address, String contact, String email, String password) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        userDAO.setSession(session);
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            User user = new User();
            user.setName(name);
            user.setAddress(address);
            user.setContact(contact);
            user.setEmail(email);
            user.setPassword(password);
            userDAO.add(user);
            transaction.commit();
        } catch (Throwable t) {
            transaction.rollback();
            t.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public User loginCheck(String email, String password) throws Exception {
        Transaction transaction = null;
        Session session = null;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            userDAO.setSession(session);
            transaction = session.beginTransaction();
            User user = userDAO.findByEmail(email);
            return user;

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;

    }


}
