package com.twu.biblioteca;

import java.util.ArrayList;

public class UserManager {
    private ArrayList<User> registerUsers;

    public UserManager(ArrayList<User> registerUsers) {
        this.registerUsers = registerUsers;
    }

    public boolean loginUser(Credential enteredCredentials) {
        return true;
    }
}
