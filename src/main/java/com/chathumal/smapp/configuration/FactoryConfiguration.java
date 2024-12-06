package com.chathumal.smapp.configuration;

import com.chathumal.smapp.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    public FactoryConfiguration() {
        Configuration configuration = new Configuration().addAnnotatedClass(User.class);
        sessionFactory = configuration.buildSessionFactory();
//        sessionFactory = new Configuration()
//                .configure("hibernate.cfg.xml")
//                .buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() {
        return factoryConfiguration == null ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}