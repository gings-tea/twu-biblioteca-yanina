package com.twu.biblioteca;

import com.twu.biblioteca.models.Book;
import com.twu.biblioteca.models.Movie;

public class LibraryMainFlow {
    private boolean isLogged;
    private String loggedUserID;
    private LibraryManager libraryManager;
    private UserManager userManager;
    private UI ui;

    public LibraryMainFlow() {
        ui = new UI();
        libraryManager = new LibraryManager();
        userManager = new UserManager();
        isLogged = false;
    }

    public void start(){
        ui.welcomeMessagePrinter();
        decideFlowBasedOnUserOption();
    }

    private void decideFlowBasedOnUserOption(){
        String optionSelected;
        do{
            optionSelected = ui.getOption(isLogged);
            switch(optionSelected){
                case "1":
                    ui.listAllBooks(libraryManager);
                    break;
                case "2":
                    checkIfLoggedToModifyAvailabilityOfBook(false);
                    break;
                case "3":
                    checkIfLoggedToModifyAvailabilityOfBook(true);
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

    private void checkIfLoggedToModifyAvailabilityOfBook(boolean availability) {
        if (isLogged)
            modifyAvailabilityOfBook(availability);
        else
            ui.printLogInFirst();
    }

    private void checkOutMovie() {
        Movie movie = ui.enterMovieInformation();
        if(libraryManager.checkOutMovie(movie)){
            ui.showSuccessMovieCheckOut();
        }
        else{
            ui.showErrorMovieCheckOut();
        }
    }

    private String getLoggedUserID() {
        if (isLogged)
            return "";
        else
            return ui.getLogInCredentials(userManager);
    }

    private void setLoggedState(String userIdLogged) {
        if(userIdLogged.isEmpty()){
            isLogged = false;
            ui.showNotLogged();
        }
        else {
            isLogged = true;
            ui.showLogged();
        }
    }

    private void modifyAvailabilityOfBook(boolean availability) {
        Book book = ui.enterLibraryBookInformation();
        if (libraryManager.changeBookAvailability(book, availability,loggedUserID)){
            ui.printSuccess(availability);
        } else {
            ui.printError(availability);
        }
    }
}
