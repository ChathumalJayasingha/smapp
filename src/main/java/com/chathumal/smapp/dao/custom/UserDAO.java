package com.chathumal.smapp.dao.custom;

import com.chathumal.smapp.dao.CrudDAO;
import com.chathumal.smapp.entity.User;

public interface UserDAO extends CrudDAO<User,String> {
    User findByEmail(String email);
}
