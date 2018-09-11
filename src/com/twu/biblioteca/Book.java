package com.twu.biblioteca;

public class Book {

    private String title;
    private String author;
    private int yearPublished;

    public Book(String title, String author, int yearPublished) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
    }

    public String[] bookDetails(){
        return new String[]{title, author, String.valueOf(yearPublished)};
    }


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
