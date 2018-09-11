package com.twu.biblioteca;

import java.util.ArrayList;

public class UserManager {
    private ArrayList<User> registerUsers;

    public UserManager(ArrayList<User> registerUsers) {
        this.registerUsers = registerUsers;
    }

    public boolean loginUser(Credential enteredCredentials) {
        boolean loggedIn = false;
        for(User u: registerUsers){
            if(u.isCredentialCorrect(enteredCredentials)){
                loggedIn = true;
            }
        }
        return loggedIn;
    }

    public String[] getUserDetailsByID(String libraryID) {
        for(User user: registerUsers){
            if(user.isLibraryIDTheSameAsUser(libraryID)){
                return user.getUserDetails(libraryID);
            }
        }
        return new String[0];
    }
}
