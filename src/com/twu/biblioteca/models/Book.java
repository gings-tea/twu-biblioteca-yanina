package com.twu.biblioteca.models;

public class Book extends AbstractLibraryItem {

    private String title;
    private String author;
    private int yearPublished;

    public Book(String title, String author, int yearPublished) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.type = "book";
    }

    @Override
    public String[] getDetails(){
        return new String[]{title, author, String.valueOf(yearPublished)};
    }

    @Override
    public boolean equals(Object otherBook){
        Book book = (Book) otherBook;
        return this.title.equals(book.title)  &&
                this.yearPublished == book.yearPublished &&
                this.author.equals(book.author) ;
    }

    public int hashCode() {
        return 0;
    }
}
