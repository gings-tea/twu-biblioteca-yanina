package com.twu.biblioteca;

import com.twu.biblioteca.models.AbstractLibraryItem;

public class LibraryMainFlow {
    private static final String BOOK = "book";
    private static final String MOVIE = "movie";
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
            switch(optionSelected.toLowerCase()){
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
                    modifyAvailabilityOfItem(false, MOVIE);
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

    private String getLoggedUserID() {
        if (isLogged)
            return userManager.NO_USER;
        else
            return ui.enterCredentialToGetID(userManager);
    }

    private void setLoggedState(String userIdLogged) {
        if(userIdLogged.equals(userManager.NO_USER)){
            isLogged = false;
            ui.showNotLogged();
        }
        else {
            isLogged = true;
            ui.showLogged();
        }
    }

    private void checkIfLoggedToModifyAvailabilityOfBook(boolean isReturning) {
        if (isLogged)
            modifyAvailabilityOfItem(isReturning, BOOK);
        else
            ui.printLogInFirst();
    }

    private void modifyAvailabilityOfItem(boolean isReturning, String type) {
        AbstractLibraryItem item = getLibraryItemByType(type);
        if (libraryManager.changeItemAvailability(item, isReturning, loggedUserID)){
            ui.printSuccess(isReturning, type);
        } else {
            ui.printError(isReturning, type);
        }
    }

    private AbstractLibraryItem getLibraryItemByType(String type){
        if(type.equals(BOOK)){
            return ui.enterLibraryBookInformation();
        }else {
            return ui.enterMovieInformation();
        }
    }
}
