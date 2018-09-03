package com.twu.biblioteca;

public class LibraryBook {

    private String title;
    private String author;
    private int yearPublished;

    public LibraryBook(String title, String author, int yearPublished) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
    }

    public String[] bookDetails(){
        return new String[]{title, author, String.valueOf(yearPublished)};
    }


    public boolean equals(Object otherBook){
        LibraryBook libraryBook = (LibraryBook) otherBook;
        return this.title.equals(libraryBook.title)  &&
                this.yearPublished == libraryBook.yearPublished &&
                this.author.equals(libraryBook.author) ;
    }

    public int hashCode() {
        return 0;
    }
}
