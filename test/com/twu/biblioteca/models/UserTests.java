package com.twu.biblioteca.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTests {
    @Test
    public void equalCredentialsShouldBeTrue() {
        User user = new User("a", "b", "+1", new Credential("id", "pass".toCharArray()));
        assertTrue(user.isCredentialCorrect(new Credential("id", "pass".toCharArray())));
    }

    @Test
    public void notEqualCredentialsShouldBeFalse() {
        User user = new User("a", "b", "+1", new Credential("id", "pass".toCharArray()));
        assertFalse(user.isCredentialCorrect(new Credential("id", "p".toCharArray())));
    }

    @Test
    public void sameIdShouldShowDetails() {
        User user = new User("a", "b", "+1", new Credential("id", "pass".toCharArray()));
        assertArrayEquals(new String[]{"a", "b", "+1"}, user.getUserDetails("id"));
    }

    @Test
    public void wrongIdShouldNotShowDetails() {
        User user = new User("a", "b", "+1", new Credential("id", "pass".toCharArray()));
        assertArrayEquals(new String[0], user.getUserDetails("i"));
    }
}
