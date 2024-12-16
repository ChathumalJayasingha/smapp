package com.chathumal.smapp.service.custom;

import com.chathumal.smapp.entity.User;

public interface UserService {
    boolean addUser(String name, String address, String contact, String email, String password, boolean fulacs) throws Exception;

    void updateUser(Integer id, String name, String address, String contact, String email, String password, boolean fulacs) throws Exception;

    User findByEmail(String email) throws Exception;


}
