package com.chathumal.smapp.service.custom.impl;


import com.chathumal.smapp.dao.DAOFactory;
import com.chathumal.smapp.dao.custom.FollowDAO;
import com.chathumal.smapp.service.custom.FollowService;




public class FollowServiceImpl implements FollowService {
    FollowDAO followDAO = (FollowDAO) DAOFactory.getInstance().getDAO(DAOFactory.Type.FOLLOW);

}
