package com.twu.biblioteca;

import com.twu.biblioteca.models.Book;
import com.twu.biblioteca.models.Credential;
import com.twu.biblioteca.models.Movie;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

class UI {
    private String welcomeMessage = "*** WELCOME TO THE TWU LIBRARY ***";
    private String commonOptions = "\n1) List all books\n2) Check out book\n3) Return book\n4) List all movies\n5) Check out movie\n6) ";
    private String formatBook = "%-25s  %-25s  %-5s \n";
    private String formatMovie = "%-25s  %-25s  %-5s %-15s\n";
    private String returnOkMsg = "Thank you for returning the book";
    private String returnErrorMsg = "That is not a valid book to return.";
    private String checkOutOkMsg = "Thank you! Enjoy the book";
    private String checkOutErrorMsg = "That book is not available.";

    void welcomeMessagePrinter(){
        System.out.println(welcomeMessage);
    }

    public String getOption(boolean isLogged){
        showOptionsIfLoggedOrNot(isLogged);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public String getLogInCredentials(UserManager userManager){
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.print("Please enter your library ID: ");
        String libraryID = sc.nextLine();
        System.out.print("Please enter your password: ");
        char[] password = sc.nextLine().toCharArray();
        return userManager.loginUser(new Credential(libraryID, password));
    }

    public void listAllBooks(LibraryManager libraryManager){
        System.out.println();
        System.out.println("Available books");
        System.out.printf(formatBook,"Title","Author","Year Published");
        libraryManager.getAvailableBookDetails("book",formatBook);
    }

    public void listAllMovies(LibraryManager libraryManager){
        System.out.println();
        System.out.println("Available movies");
        System.out.printf(formatMovie,"Name", "Director", "Year", "Rate");
        libraryManager.getAvailableMovieDetails("movie", formatMovie);
    }

    public Book enterLibraryBookInformation(){
        Scanner sc = new  Scanner(System.in);
        System.out.println();
        System.out.print("Please enter the book title: ");
        String title = sc.nextLine();
        System.out.print("Please enter the book author/s: ");
        String author = sc.nextLine();
        System.out.print("Please enter the book year of publication: ");
        int yearPublished = Integer.valueOf(sc.nextLine());
        return new Book(title, author, yearPublished);
    }

    public Movie enterMovieInformation() {
        Scanner sc = new  Scanner(System.in);
        System.out.println();
        System.out.print("Please enter the movie name: ");
        String name = sc.nextLine();
        System.out.print("Please enter the movie director: ");
        String director = sc.nextLine();
        System.out.print("Please enter the movie year: ");
        int year = Integer.valueOf(sc.nextLine());
        System.out.print("Please enter the movie rate if applies: ");
        String rate = sc.nextLine();
        return getMovie(name, director, year, rate);
    }

    @NotNull
    private Movie getMovie(String name, String director, int year, String rate) {
        if(rate.isEmpty()){
            return new Movie(name, director, year);
        }
        else {
            return new Movie(name, director, year, rate);
        }
    }

    public void printSuccess(boolean isReturning){
        if(isReturning){
            System.out.println(returnOkMsg);
        }
        else {
            System.out.println(checkOutOkMsg);
        }
    }

    public void printError(boolean isReturning){
        if(isReturning){
            System.out.println(returnErrorMsg);
        }
        else {
            System.out.println(checkOutErrorMsg);
        }
    }

    public boolean isNotQuitting(String optionSelected) {
        return !optionSelected.toLowerCase().equals("q");
    }

    public void showLogged() {
        System.out.println();
        System.out.println("You're now logged in");
    }

    public void showNotLogged() {
        System.out.println();
        System.out.println("You're not logged");
    }

    public void showNotCorrectOptionMsg(){
        System.out.println();
        System.out.println("Select a valid option!");
        System.out.println();
    }

    private void showOptionsIfLoggedOrNot(boolean isLogged){
        if(isLogged){
            System.out.print(commonOptions + "Log out\n7) See your contact information\nPlease enter an option or enter Q to quit: ");
        }
        else {
            System.out.print(commonOptions + "Log In\nPlease enter an option or enter Q to quit: ");
        }
    }

    public void showUserDetails(UserManager userManager, String loggedUserID) {
        System.out.println();
        System.out.printf("Name: %-30s\nEmail: %-30s\nPhone: %-30s", userManager.getUserDetailsByID(loggedUserID));
        System.out.println();
    }

    public void showSuccessMovieCheckOut() {
        System.out.println();
        System.out.println("Thank you! Enjoy the movie");
    }

    public void showErrorMovieCheckOut() {
        System.out.println("That movie is not available.");
    }

    public void printLogInFirst() {
        System.out.println("Please log in first");
    }
}
