package com.chathumal.smapp.dao.custom;

import com.chathumal.smapp.dao.CrudDAO;
import com.chathumal.smapp.dto.FollowerDTO;
import com.chathumal.smapp.entity.Follow;
import com.chathumal.smapp.entity.User;
import com.chathumal.smapp.exception.NotFoundException;

import java.util.List;


public interface FollowDAO extends CrudDAO<Follow,Integer> {
    List<FollowerDTO> findByFollower(User user) throws NotFoundException;

}
