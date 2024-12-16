package com.chathumal.smapp.service.custom.impl;

import com.chathumal.smapp.configuration.FactoryConfiguration;
import com.chathumal.smapp.dao.DAOFactory;
import com.chathumal.smapp.dao.custom.ContentDAO;
import com.chathumal.smapp.dao.custom.impl.ContentDAOImpl;
import com.chathumal.smapp.dao.custom.impl.UserDAOImpl;
import com.chathumal.smapp.entity.Content;
import com.chathumal.smapp.entity.User;
import com.chathumal.smapp.exception.DuplicateEntryException;
import com.chathumal.smapp.exception.NotFoundException;
import com.chathumal.smapp.service.custom.ContentService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ContentServiceImpl implements ContentService {

    @Override
    public boolean addContent(User user, String content) throws Exception {
        ContentDAO contentDAO = (ContentDAO) DAOFactory.getInstance().getDAO(DAOFactory.Type.CONTENT);
        Session session = FactoryConfiguration.getInstance().getSession();
        contentDAO.setSession(session);
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Content contentEntity = new Content();
            contentEntity.setUser(user);
            contentEntity.setContent(content);
            session.save(contentEntity);
            transaction.commit();
            return true;
        } catch (Exception t) {
            transaction.rollback();
            t.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public List<Content> findContent(User user) throws Exception {
        ContentDAO contentDAO = (ContentDAO) DAOFactory.getInstance().getDAO(DAOFactory.Type.CONTENT);
        Session session = FactoryConfiguration.getInstance().getSession();
        contentDAO.setSession(session);
        List<Content> contents = new ArrayList<>();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            List<Content> all = contentDAO.getAll();
            for (int i = 0; i < all.size(); i++) {
                Content content = all.get(i);
                boolean equals = content.getUser().getId()==(user.getId());
                if (equals) contents.add(content);
            }
            return contents;
        } catch (Exception t) {
            transaction.rollback();
            t.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
}
