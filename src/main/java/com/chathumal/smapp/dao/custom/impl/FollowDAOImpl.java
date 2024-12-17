package com.chathumal.smapp.dao.custom.impl;

import com.chathumal.smapp.dao.CrudDAOImpl;
import com.chathumal.smapp.dao.custom.FollowDAO;
import com.chathumal.smapp.dto.FollowerDTO;
import com.chathumal.smapp.dto.FollowingAndUnfollowingDTO;
import com.chathumal.smapp.entity.Follow;
import com.chathumal.smapp.entity.User;
import com.chathumal.smapp.exception.NotFoundException;
import org.hibernate.query.NativeQuery;

import java.util.ArrayList;
import java.util.List;

public class FollowDAOImpl extends CrudDAOImpl<Follow, Integer> implements FollowDAO {
    @Override
    public List<FollowerDTO> findByFollower(User user) throws NotFoundException {
        try {
            List<FollowerDTO> followerDTOS = new ArrayList<>();
            NativeQuery nativeQuery = session.createNativeQuery("select * from follow f inner join content c where f.user_id=c.user_id and f.flwr_id=" + user.getId());
            List<Object[]> rows = nativeQuery.list();
            if (rows.size() == 0) {
                throw new NotFoundException("Cannot found");
            } else {
                for (Object[] row : rows) {
                    FollowerDTO dto = new FollowerDTO();
                    dto.setFid(row[0].toString());
                    dto.setFlwr_id(row[1].toString());
                    dto.setUser_id(row[2].toString());
                    dto.setCid(row[3].toString());
                    dto.setContent(row[5].toString());
                    followerDTOS.add(dto);
                }
            }
            return followerDTOS;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

    }
}
