package com.twu.biblioteca.models;

public class Movie extends AbstractLibraryItem {
    private String name;
    private String director;
    private int year;
    private String rate;

    public Movie(String name, String director, int year, String rate) {
        this.name = name;
        this.director = director;
        this.year = year;
        this.rate = rate;
        this.type = "movie";
    }

    public Movie(String name, String director, int year) {
        this.name = name;
        this.director = director;
        this.year = year;
        rate = "unrated";
        this.type = "movie";
    }

    @Override
    public boolean equals(Object otherMovie){
        AbstractLibraryItem movie = (AbstractLibraryItem) otherMovie;
        if(movie.isSameType(this.type)){
            return isEqualContent((Movie) movie);

        }
        return false;
    }

    private boolean isEqualContent(Movie movie){
        return this.name.equals(movie.name)  &&
                this.year == movie.year &&
                this.director.equals(movie.director) &&
                this.rate.equals(movie.rate);
    }

    public int hashCode() {
        return 0;
    }

    @Override
    public String[] getDetails() {
        return new String[]{name, director, String.valueOf(year), rate};
    }
}
