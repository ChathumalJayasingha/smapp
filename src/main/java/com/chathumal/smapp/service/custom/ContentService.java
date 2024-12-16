package com.chathumal.smapp.service.custom;

import com.chathumal.smapp.entity.Content;
import com.chathumal.smapp.entity.User;

import java.util.List;

public interface ContentService {
    boolean addContent(User user, String content) throws Exception;

    List<Content> findContent(User user) throws Exception;

}
