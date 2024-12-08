package com.chathumal.smapp.dao.custom.impl;

import com.chathumal.smapp.dao.CrudDAOImpl;
import com.chathumal.smapp.dao.custom.UserDAO;
import com.chathumal.smapp.entity.User;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class UserDAOImpl extends CrudDAOImpl<User,String> implements UserDAO {


    @Override
    public User findByEmail(String email) {
        NativeQuery nativeQuery = session.createNativeQuery("select * from user where email='"+email+"'");
        List<Object[]> rows = nativeQuery.list();
        User user = new User();
        for(Object[] row : rows){
            user.setId(Integer.parseInt(row[1].toString()));
            user.setFulacs(Boolean.parseBoolean(row[0].toString()));
            user.setAddress(row[2].toString());
            user.setContact(row[3].toString());
            user.setEmail(row[4].toString());
            user.setName(row[5].toString());
            user.setPassword(row[6].toString());
        }
        System.out.println("user = " + user);
        return user;
    }
}
