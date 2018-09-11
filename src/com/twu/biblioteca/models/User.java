package com.twu.biblioteca.models;

public class User {
    private String name;
    private String email;
    private String phone;
    private Credential credentials;

    public User(String name, String email, String phone, Credential credentials) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.credentials = credentials;
    }

    public boolean isCredentialCorrect(Credential credential){
        return this.credentials.equals(credential);
    }

    public String[] getUserDetails(String libraryID){
        if(isLibraryIDTheSameAsUser(libraryID)){
            return new String[]{name, email, phone};
        }
        return new String[0];
    }

    public boolean isLibraryIDTheSameAsUser(String libraryID){
        return credentials.getLibraryNumber().equals(libraryID);
    }
}
