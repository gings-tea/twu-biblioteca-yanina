package com.twu.biblioteca;

import com.twu.biblioteca.models.Book;
import com.twu.biblioteca.models.Movie;

import java.util.ArrayList;
import java.util.Hashtable;

public class LibraryManager {
    private Hashtable<Book, String> checkedOutBooks;
    private ArrayList<Book> availableBooks;

    private ArrayList<Movie> availableMovies;

    public LibraryManager() {
        fillLibraryItems();
    }

    public LibraryManager(ArrayList availableBooks, Hashtable checkedOutBooks, ArrayList availableMovies){
        this.availableBooks = availableBooks;
        this.checkedOutBooks = checkedOutBooks;
        this.availableMovies = availableMovies;
    }

    public boolean isBookInLibraryAvailable(Book book) {
        return availableBooks.contains(book);
    }


    public boolean changeAvailability(Book book, boolean returning, String libraryId) {
        boolean changed;
        if(returning){
            changed = returnBook(book);
        } else {
            changed = checkOutBook(book,libraryId);
        }
        return changed;
    }

    public void getAvailableBookDetails(String format) {
        for(Book book: availableBooks) {
            System.out.printf(format, book.bookDetails());
        }
    }


    public void getAvailableMovieDetails(String format) {
        for(Movie movie: availableMovies) {
            System.out.printf(format, movie.movieDetails());
        }
    }

    private boolean isBookInLibraryNotAvailable(Book book){
        return checkedOutBooks.containsKey(book);
    }

    private boolean returnBook(Book book) {
        if(isBookInLibraryNotAvailable(book)){
            checkedOutBooks.remove(book);
            availableBooks.add(book);
            return true;
        }
        return false;
    }

    private boolean checkOutBook(Book book, String libraryId) {
        if(isBookInLibraryAvailable(book)){
            availableBooks.remove(book);
            checkedOutBooks.put(book, libraryId);
            return true;
        }
        return false;
    }

    private void fillLibraryItems(){

        availableBooks = new ArrayList<>();
        availableBooks.add(new Book("TDD", "Kent Beck", 2003));
        availableBooks.add(new Book("Crime and Punishment", "Fyodor Dostoyevsky", 1935));

        checkedOutBooks = new Hashtable<>();

        checkedOutBooks.put(new Book("Go in Action", "William Kennedy", 2010),"111-1111");
        checkedOutBooks.put(new Book("Harry Potter I", "J K Rowling", 2000),"111-1112");
        checkedOutBooks.put(new Book("Java", "Kathy Sierra", 2001),"111-1113");

        availableMovies = new ArrayList<>();
        availableMovies.add(new Movie("The Godfather", "Coppola", 1972, "9.2"));
        availableMovies.add(new Movie("Gone Girl", "David Fincher", 2014, "8..1"));
        
    }
}
