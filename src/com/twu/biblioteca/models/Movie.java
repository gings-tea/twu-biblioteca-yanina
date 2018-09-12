package com.twu.biblioteca.models;

public class Movie {
    private String name;
    private String director;
    private int year;
    private int rate;

    public Movie(String name, String director, int year, int rate) {
        this.name = name;
        this.director = director;
        this.year = year;
        this.rate = rate;
    }

    public String[] movieDetails(){
        return new String[]{name, director, String.valueOf(year), String.valueOf(rate)};
    }

    @Override
    public boolean equals(Object otherMovie){
        Movie movie = (Movie) otherMovie;
        return this.name.equals(movie.name)  &&
                this.year == movie.year &&
                this.director.equals(movie.director) &&
                this.rate == movie.rate;
    }

    public int hashCode() {
        return 0;
    }
}
