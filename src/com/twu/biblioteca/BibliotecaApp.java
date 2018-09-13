package com.twu.biblioteca;

import com.twu.biblioteca.models.Book;
import com.twu.biblioteca.models.Movie;


public class BibliotecaApp {

    private static boolean isLogged;
    private static String loggedUserID;
    private static LibraryManager libraryManager;
    private static UserManager userManager;
    private static UI ui;

    public static void main(String[] args) {
        ui = new UI();
        libraryManager = new LibraryManager();
        userManager = new UserManager();
        isLogged = false;
        ui.welcomeMessagePrinter();
        decideFlowBasedOnUserOption();
    }

    private static void decideFlowBasedOnUserOption(){
        String optionSelected;
        do{
            optionSelected = ui.getOption(isLogged);
            switch(optionSelected){
                case "1":
                    ui.listAllBooks(libraryManager);
                    break;
                case "2":
                    if(isLogged)
                        modifyAvailabilityOfBook(false);
                    else
                        System.out.println("Please log in first");
                    break;
                case "3":
                    if(isLogged)
                        modifyAvailabilityOfBook(true);
                    else
                        System.out.println("Please log in first");
                    break;
                case "4":
                    ui.listAllMovies(libraryManager);
                    break;
                case "5":
                    checkOutMovie();
                    break;
                case "6":
                    loggedUserID = getLoggedUserID();
                    setLoggedState(loggedUserID);
                    break;
                case "7":
                    if (isLogged){
                        ui.showUserDetails(userManager, loggedUserID);
                    }
                    else {
                        ui.showNotCorrectOptionMsg();
                    }
                    break;
                case "q":
                    break;
                default:
                    ui.showNotCorrectOptionMsg();
            }
        } while(ui.isNotQuitting(optionSelected));
    }

    private static void checkOutMovie() {
        Movie movie = ui.enterMovieInformation();
        if(libraryManager.checkOutMovie(movie)){
            ui.showSuccessMovieCheckOut();
        }
        else{
            ui.showErrorMovieCheckOut();
        }

    }

    private static String getLoggedUserID() {
        if (isLogged)
            return "";
        else
            return ui.getLogInCredentials(userManager);
    }

    private static void setLoggedState(String userIdLogged) {
        if(userIdLogged.isEmpty()){
            isLogged = false;
            ui.showNotLogged();
        }
        else {
            isLogged = true;
            ui.showLogged();
        }
    }

    private static void modifyAvailabilityOfBook(boolean availability) {
        Book book = ui.enterLibraryBookInformation();
        if (libraryManager.changeBookAvailability(book, availability,loggedUserID)){
            ui.printSuccess(availability);
        } else {
            ui.printError(availability);
        }
    }
}
