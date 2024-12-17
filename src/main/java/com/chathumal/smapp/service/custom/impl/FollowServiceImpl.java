package com.chathumal.smapp.service.custom.impl;


import com.chathumal.smapp.configuration.FactoryConfiguration;
import com.chathumal.smapp.dao.DAOFactory;
import com.chathumal.smapp.dao.custom.ContentDAO;
import com.chathumal.smapp.dao.custom.FollowDAO;
import com.chathumal.smapp.dto.FollowerDTO;
import com.chathumal.smapp.entity.Follow;
import com.chathumal.smapp.entity.User;
import com.chathumal.smapp.exception.NotFoundException;
import com.chathumal.smapp.service.custom.FollowService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class FollowServiceImpl implements FollowService {
    FollowDAO followDAO = (FollowDAO) DAOFactory.getInstance().getDAO(DAOFactory.Type.FOLLOW);

    @Override
    public List<FollowerDTO> findByFollower(User user) throws NotFoundException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            followDAO.setSession(session);
            transaction = session.beginTransaction();
            List<FollowerDTO> byFollower = followDAO.findByFollower(user);
            return byFollower;
        } catch (NotFoundException e) {
            transaction.rollback();
            e.printStackTrace();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean unfollowUser(Integer fid) throws NotFoundException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            followDAO.setSession(session);
            transaction = session.beginTransaction();
            List<Follow> all = followDAO.getAll();
            for (int i = 0; i < all.size(); i++) {
                Follow follow = all.get(i);
                if (fid==follow.getFid()) {
                    followDAO.delete(fid);
                }
            }
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean addFollow(User following, User follower) throws Exception {
        Session session = null;
        Transaction transaction = null;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            followDAO.setSession(session);
            transaction = session.beginTransaction();
            Follow follow = new Follow();
            follow.setFlwusr(following);
            follow.setUser(follower);
            followDAO.add(follow);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }
}
