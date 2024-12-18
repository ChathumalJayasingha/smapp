package com.chathumal.smapp.service.custom;


import com.chathumal.smapp.dto.FollowerDTO;
import com.chathumal.smapp.entity.User;
import com.chathumal.smapp.exception.NotFoundException;

import java.util.List;

public interface FollowService {
    List<FollowerDTO> findByFollower(User user) throws NotFoundException;
    boolean unfollowUser(Integer fid) throws NotFoundException;
    boolean addFollow(User following,User follower) throws Exception;
    boolean deleteFollow(User following,User follower) throws Exception;


}
