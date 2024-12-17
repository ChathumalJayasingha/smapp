package com.chathumal.smapp.service.custom;

import com.chathumal.smapp.dto.FollowingAndUnfollowingDTO;
import com.chathumal.smapp.entity.User;
import com.chathumal.smapp.exception.NotFoundException;

import java.util.List;

public interface UserService {
    boolean addUser(String name, String address, String contact, String email, String password, boolean fulacs) throws Exception;

    void updateUser(Integer id, String name, String address, String contact, String email, String password, boolean fulacs) throws Exception;

    User findByEmail(String email) throws Exception;
    List<User> findAllUsers() throws Exception;

    List<FollowingAndUnfollowingDTO> findByFollowedAndUnFollowedUser(String userId) throws NotFoundException ;



    }
