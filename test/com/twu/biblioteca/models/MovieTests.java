package com.twu.biblioteca.models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MovieTests {
    @Test
    public void ifCreateMovieWithoutRateShouldAppearAsUnrated() {
        Movie movie = new Movie("movie", "director", 2000);
        assertEquals(new String[]{"movie", "director", "2000","unrated"}, movie.movieDetails());
    }
}
