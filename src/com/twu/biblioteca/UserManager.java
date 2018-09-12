package com.twu.biblioteca;

import com.twu.biblioteca.models.Credential;
import com.twu.biblioteca.models.User;

import java.util.ArrayList;

public class UserManager {
    private ArrayList<User> registerUsers;

    public UserManager() {
        fillUsers();
    }

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

    private void fillUsers(){
        registerUsers = new ArrayList<>();
        registerUsers.add(new User("Yanina", "a@a.com", "+1234", new Credential("123-4567","password".toCharArray())));
        registerUsers.add(new User("Collen", "email@a.com", "+9876", new Credential("987-6543","pass".toCharArray())));
    }
}
