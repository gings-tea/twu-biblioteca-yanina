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
    userList.add(new User("name2", "email2", "+9876", new Credential("111-1112","pass".toCharArray())));
    userManager = new UserManager(userList);
    }

    @Test
    public void loginUserIfExists() {
        assertEquals(true, userManager.loginUser(new Credential("111-1111","password".toCharArray())));
    }

    @Test
    public void otherUserLogIn() {
        assertEquals(true, userManager.loginUser(new Credential("111-1112","pass".toCharArray())));
    }
}
