package com.twu.biblioteca;

import com.twu.biblioteca.models.AbstractLibraryItem;
import com.twu.biblioteca.models.Book;
import com.twu.biblioteca.models.Movie;

import java.util.ArrayList;
import java.util.Hashtable;

public class LibraryManager {

    private ArrayList<Movie> availableMovies;
    private ArrayList<Movie> checkedOutMovies;

    private ArrayList<AbstractLibraryItem> availableItems;
    private Hashtable<AbstractLibraryItem, String> checkedOutItems;

    public LibraryManager() {
        fillLibraryItems();
    }

    public LibraryManager(ArrayList availableBooks, Hashtable checkedOutBooks, ArrayList availableMovies){
        this.availableMovies = availableMovies;
        availableItems = availableBooks;
        checkedOutItems = checkedOutBooks;
        checkedOutMovies = new ArrayList<>();
    }

    public boolean isBookInLibraryAvailable(AbstractLibraryItem item) {
        return availableItems.contains(item);
    }


    public boolean isMovieInLibraryAvailable(Movie movie) {
        return availableMovies.contains(movie);
    }

    public boolean changeBookAvailability(AbstractLibraryItem item, boolean returning, String libraryId) {
        boolean changed;
        if(returning){
            changed = returnBook(item);
        } else {
            changed = checkOutBook(item,libraryId);
        }
        return changed;
    }

    public void getAvailableBookDetails(String type, String format) {
        for(AbstractLibraryItem item: availableItems) {
            if(item.isSameType(type))
                System.out.printf(format, item.getDetails());
        }
    }


    public void getAvailableMovieDetails(String type, String format) {
        for(Movie movie: availableMovies) {
            System.out.printf(format, movie.getDetails());
        }
    }

    private boolean isBookInLibraryNotAvailable(AbstractLibraryItem item){
        return checkedOutItems.containsKey(item);
    }

    private boolean returnBook(AbstractLibraryItem item) {
        if(isBookInLibraryNotAvailable(item)){
            checkedOutItems.remove(item);
            availableItems.add(item);
            return true;
        }
        return false;
    }

    private boolean checkOutBook(AbstractLibraryItem item, String libraryId) {
        if(isBookInLibraryAvailable(item)){
            availableItems.remove(item);
            checkedOutItems.put(item, libraryId);
            return true;
        }
        return false;
    }

    public boolean checkOutMovie(Movie movie) {
        if(isMovieInLibraryAvailable(movie)){
            availableMovies.remove(movie);
            checkedOutMovies.add(movie);
            return true;
        }
        return false;
    }

    private void fillLibraryItems(){

        availableItems = new ArrayList<>();
        availableItems.add(new Book("TDD", "Kent Beck", 2003));
        availableItems.add(new Book("Crime and Punishment", "Fyodor Dostoyevsky", 1935));

        checkedOutItems = new Hashtable<>();

        checkedOutItems.put(new Book("Go in Action", "William Kennedy", 2010),"111-1111");
        checkedOutItems.put(new Book("Harry Potter I", "J K Rowling", 2000),"111-1112");
        checkedOutItems.put(new Book("Java", "Kathy Sierra", 2001),"111-1113");

        availableMovies = new ArrayList<>();
        availableMovies.add(new Movie("The Godfather", "Coppola", 1972, "9.2"));
        availableMovies.add(new Movie("Gone Girl", "David Fincher", 2014, "8..1"));

        checkedOutMovies = new ArrayList<>();

    }
}
