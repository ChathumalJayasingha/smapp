package com.chathumal.smapp.service;

import com.chathumal.smapp.service.custom.impl.ContentServiceImpl;
import com.chathumal.smapp.service.custom.impl.FollowServiceImpl;
import com.chathumal.smapp.service.custom.impl.UserServiceImpl;

public class ServiceFactory {

    private static ServiceFactory ServiceFactory;

    private ServiceFactory() {

    }

    public static ServiceFactory getInstance() {
        return (ServiceFactory == null) ? ServiceFactory = new ServiceFactory() : ServiceFactory;
    }

    public enum Type {USER, CONTENT, FOLLOW}

    public Object getService(Type type) {
        switch (type) {
            case USER:
                return new UserServiceImpl();
            case CONTENT:
                return new ContentServiceImpl();
            case FOLLOW:
                return new FollowServiceImpl();
            default:
                return null;
        }
    }
}
