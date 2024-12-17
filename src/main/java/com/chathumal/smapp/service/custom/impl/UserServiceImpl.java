package com.chathumal.smapp.service.custom.impl;

import com.chathumal.smapp.configuration.FactoryConfiguration;
import com.chathumal.smapp.dao.DAOFactory;
import com.chathumal.smapp.dao.custom.UserDAO;
import com.chathumal.smapp.dto.FollowingAndUnfollowingDTO;
import com.chathumal.smapp.entity.User;
import com.chathumal.smapp.exception.DuplicateEntryException;
import com.chathumal.smapp.exception.NotFoundException;
import com.chathumal.smapp.service.custom.UserService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public class UserServiceImpl implements UserService {

    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.Type.USER);

    @Override
    public boolean addUser(String name, String address, String contact, String email, String password, boolean fulacs) throws DuplicateEntryException,Exception {
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
            user.setFulacs(fulacs);
            userDAO.add(user);
            transaction.commit();
            return true;
        } catch (ConstraintViolationException t) {
            transaction.rollback();
            t.printStackTrace();
            throw new DuplicateEntryException("Please check email and mobile number");
        } catch (Exception t) {
            transaction.rollback();
            t.printStackTrace();
            throw new DuplicateEntryException(t.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public void updateUser(Integer id,String name, String address, String contact, String email, String password, boolean fulacs) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        userDAO.setSession(session);
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            User user = new User();
            user.setId(id);
            user.setName(name);
            user.setAddress(address);
            user.setContact(contact);
            user.setEmail(email);
            user.setPassword(password);
            user.setFulacs(fulacs);
            userDAO.update(user);
            transaction.commit();
        } catch (Throwable t) {
            transaction.rollback();
            t.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public User findByEmail(String email) throws NotFoundException {
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


    @Override
    public List<User> findAllUsers() throws Exception {
        Transaction transaction = null;
        Session session = null;
        List<User> userList= null;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            userDAO.setSession(session);
            transaction = session.beginTransaction();
            userList = userDAO.getAll();
            return userList;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<FollowingAndUnfollowingDTO> findByFollowedAndUnFollowedUser(String userId) throws NotFoundException {
        Transaction transaction = null;
        Session session = null;
        List<FollowingAndUnfollowingDTO> userList= null;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            userDAO.setSession(session);
            transaction = session.beginTransaction();
            userList = userDAO.findByFollowedAndUnFollowedUser(userId);
            transaction.commit();
            return userList;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

}
