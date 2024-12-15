package com.chathumal.smapp.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserSessionTest  {

    @Test
    void testSingletonInstance() {
        UserSession instance1 = UserSession.getInstance();
        UserSession instance2 = UserSession.getInstance();

        assertSame(instance1, instance2, "Instances are not the same!");
    }

    @Test
    void testSetAndGetUsername() {
        UserSession userSession = UserSession.getInstance();
        String testUsername = "testUser";
        userSession.setUsername(testUsername);

        assertEquals(testUsername, userSession.getUsername(), "The retrieved username is incorrect!");
    }

    @Test
    void testDefaultUsername() {
        UserSession userSession = UserSession.getInstance();

        assertNull(userSession.getUsername(), "Default username is not null!");
    }}
