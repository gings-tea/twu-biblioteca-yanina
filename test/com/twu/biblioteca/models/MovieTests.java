package com.twu.biblioteca.models;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;

public class MovieTests {
    @Test
    public void ifCreateMovieWithoutRateShouldAppearAsUnrated() {
        Movie movie = new Movie("movie", "director", 2000);
        assertArrayEquals(new String[]{"movie", "director", "2000","unrated"}, movie.movieDetails());
    }

    @Test
    public void compareTwoEqualMoviesShouldReturnTrue() {
        assertTrue(new Movie("movie", "director", 2000).equals(new Movie("movie", "director", 2000)));
    }
    @Test
    public void compareTwoDifferentMoviesShouldReturnFalse() {
        assertFalse(new Movie("movie", "director", 2000).equals(new Movie("movie", "director", 1000,"1")));
    }
}
