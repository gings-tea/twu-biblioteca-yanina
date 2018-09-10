package com.twu.biblioteca;

public class Credential {
    private String libraryNumber;
    private char[] password;

    public Credential(String libraryNumber, char[] password) {
        this.libraryNumber = libraryNumber;
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        Credential otherCredential = (Credential) obj;
        return this.libraryNumber.equals(otherCredential.libraryNumber) &&
                this.password.equals(otherCredential.password);
    }
}
