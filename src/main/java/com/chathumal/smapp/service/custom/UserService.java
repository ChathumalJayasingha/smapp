package com.chathumal.smapp.service.custom;

import com.chathumal.smapp.entity.User;

import java.util.List;

public interface UserService {
    boolean addUser(String name, String address, String contact, String email, String password, boolean fulacs) throws Exception;

    void updateUser(Integer id, String name, String address, String contact, String email, String password, boolean fulacs) throws Exception;

    User findByEmail(String email) throws Exception;
    List<User> findAllUsers() throws Exception;


}
