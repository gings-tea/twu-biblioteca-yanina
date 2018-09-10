package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class UserManagerTests {

    private UserManager userManager;

    @Before
    public void beforeTest(){
    ArrayList<User> userList = new ArrayList<>();
    userList.add(new User("name", "email", "+1234", new Credential("111-1111","password".toCharArray())));
    userManager = new UserManager(userList);
    }

    @Test
    public void loginUserIfExists() {
        assertEquals(true, userManager.loginUser(new Credential("111-1111","password".toCharArray())));
    }
}
