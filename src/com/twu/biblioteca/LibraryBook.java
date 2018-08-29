package com.twu.biblioteca;

public class LibraryBook {

    private String title;
    private String author;
    private int yearPublished;
    private boolean checkedOut;

    public LibraryBook(String title, String author, int yearPublished, boolean checkedOut) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.checkedOut = checkedOut;
    }


    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }
}
