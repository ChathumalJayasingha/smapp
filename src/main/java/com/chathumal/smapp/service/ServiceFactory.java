package com.chathumal.smapp.service;

import com.chathumal.smapp.service.custom.impl.UserServiceImpl;

public class ServiceFactory {

    private static ServiceFactory ServiceFactory;
    private ServiceFactory() {

    }
    public static ServiceFactory getInstance() {
        return (ServiceFactory == null) ? ServiceFactory = new ServiceFactory() : ServiceFactory;
    }

    public enum Type{USER}

    public Object getService(Type type) {
        switch (type) {
            case USER:return new UserServiceImpl();
            default: return null;
        }
    }
}
