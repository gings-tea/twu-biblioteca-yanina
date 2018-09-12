package com.twu.biblioteca;

import com.twu.biblioteca.models.Credential;
import com.twu.biblioteca.models.User;
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
        assertEquals("111-1111", userManager.loginUser(new Credential("111-1111","password".toCharArray())));
    }

    @Test
    public void otherUserLogIn() {
        assertEquals("111-1112", userManager.loginUser(new Credential("111-1112","pass".toCharArray())));
    }

    @Test
    public void getLoggedUserDetails() {
        assertEquals(new String[]{"name", "email", "+1234"}, userManager.getUserDetailsByID("111-1111"));
    }
}
