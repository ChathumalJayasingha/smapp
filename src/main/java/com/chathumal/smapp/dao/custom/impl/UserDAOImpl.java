package com.chathumal.smapp.dao.custom.impl;

import com.chathumal.smapp.dao.CrudDAOImpl;
import com.chathumal.smapp.dao.custom.UserDAO;
import com.chathumal.smapp.dto.FollowerDTO;
import com.chathumal.smapp.dto.FollowingAndUnfollowingDTO;
import com.chathumal.smapp.entity.User;
import com.chathumal.smapp.exception.NotFoundException;
import org.hibernate.query.NativeQuery;

import java.util.ArrayList;
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

    @Override
    public List<FollowingAndUnfollowingDTO> findByFollowedAndUnFollowedUser(String userId) throws NotFoundException {
        try {
            List<FollowingAndUnfollowingDTO> list = new ArrayList<>();
            NativeQuery nativeQuery = session.createNativeQuery("SELECT *, CASE WHEN id IN (SELECT f.user_id FROM follow f INNER JOIN user u ON f.flwr_id = u.id AND u.id = '"+userId+"' ) \n" +
                    "THEN 'true' ELSE 'false' END AS following FROM user where id<>"+userId);
            List<Object[]> rows = nativeQuery.list();
            if (rows.size() == 0) {
                throw new NotFoundException("Cannot found");
            } else {
                for (Object[] row : rows) {
                    FollowingAndUnfollowingDTO user = new FollowingAndUnfollowingDTO();
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
                    user.setFollowing(row[7].toString());
                    list.add(user);
                }
            }
            return list;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
