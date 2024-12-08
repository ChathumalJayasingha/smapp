module com.chathumal.smapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.bootstrapfx.core;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;
    requires mysql.connector.java;
    requires java.sql;
    requires org.junit.jupiter.api;
    requires com.jfoenix;
    requires org.junit.platform.commons;
    requires org.junit.jupiter.engine;
    requires org.junit.platform.engine;
    requires static lombok;


    opens com.chathumal.smapp.entity to org.hibernate.orm.core;
    opens com.chathumal.smapp.controller to javafx.fxml;
    opens com.chathumal.smapp.dao.custom.impl to org.junit.platform.commons;

    exports com.chathumal.smapp;
    exports com.chathumal.smapp.controller;
    exports com.chathumal.smapp.entity;
    exports com.chathumal.smapp.configuration;
}
