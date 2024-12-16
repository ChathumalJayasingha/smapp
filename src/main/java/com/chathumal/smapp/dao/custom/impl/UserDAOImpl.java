package com.chathumal.smapp.dao.custom.impl;

import com.chathumal.smapp.dao.CrudDAOImpl;
import com.chathumal.smapp.dao.custom.UserDAO;
import com.chathumal.smapp.entity.User;
import com.chathumal.smapp.exception.NotFoundException;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class UserDAOImpl extends CrudDAOImpl<User, String> implements UserDAO {


    @Override
    public User findByEmail(String email) throws NotFoundException {
        User user = new User();
        NativeQuery nativeQuery = session.createNativeQuery("select * from user where email='" + email + "'");
        List<Object[]> rows = nativeQuery.list();
        if (rows.size() == 0) {
            throw new NotFoundException("User cannot found");
        } else {
            for (Object[] row : rows) {
                user.setId(Integer.parseInt(row[1].toString()));
                String fulacs = row[0].toString();
                if (fulacs.equalsIgnoreCase("1")) {
                    user.setFulacs(true);
                } else {
                    user.setFulacs(false);
                }
                user.setAddress(row[2].toString());
                user.setContact(row[3].toString());
                user.setEmail(row[4].toString());
                user.setName(row[5].toString());
                user.setPassword(row[6].toString());
            }
        }
        return user;
    }
}
