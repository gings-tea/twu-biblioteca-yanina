package com.twu.biblioteca;

import java.util.ArrayList;

public class UserManager {
    private ArrayList<User> registerUsers;

    public UserManager(ArrayList<User> registerUsers) {
        this.registerUsers = registerUsers;
    }

    public boolean loginUser(Credential enteredCredentials) {
        boolean logedIn = false;
        for(User u: registerUsers){
            if(u.isCredentialCorrect(enteredCredentials)){
                logedIn = true;
            }
        }
        return logedIn;
    }

    public String[] getUserDetailsByID(String libraryID) {
        return new String[]{"name", "email", "+1234"};
    }
}
