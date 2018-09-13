package com.twu.biblioteca.models;

public class Movie {
    private String name;
    private String director;
    private int year;
    private String rate;

    public Movie(String name, String director, int year, String rate) {
        this.name = name;
        this.director = director;
        this.year = year;
        this.rate = rate;
    }

    public Movie(String name, String director, int year) {
        this.name = name;
        this.director = director;
        this.year = year;
        rate = "unrated";
    }

    public String[] movieDetails(){
        return new String[]{name, director, String.valueOf(year), rate};
    }

    @Override
    public boolean equals(Object otherMovie){
        Movie movie = (Movie) otherMovie;
        return this.name.equals(movie.name)  &&
                this.year == movie.year &&
                this.director.equals(movie.director) &&
                this.rate.equals(movie.rate);
    }

    public int hashCode() {
        return 0;
    }
}
