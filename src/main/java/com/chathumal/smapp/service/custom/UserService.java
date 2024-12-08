package com.chathumal.smapp.service.custom;

import com.chathumal.smapp.entity.User;

public interface UserService {
    void addUser(String name, String address, String contact, String email, String password) throws Exception;
    User loginCheck(String email, String password) throws Exception;


}
