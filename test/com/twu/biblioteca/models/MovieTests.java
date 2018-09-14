package com.twu.biblioteca.models;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

public class MovieTests {
    @Test
    public void ifCreateMovieWithoutRateShouldAppearAsUnrated() {
        Movie movie = new Movie("movie", "director", 2000);
        assertArrayEquals(new String[]{"movie", "director", "2000","unrated"}, movie.getDetails());
    }

    @Test
    public void compareTwoEqualMoviesShouldReturnTrue() {
        assertEquals(new Movie("movie", "director", 2000), new Movie("movie", "director", 2000));
    }
    @Test
    public void compareTwoDifferentMoviesShouldReturnFalse() {
        assertNotEquals(new Movie("movie", "director", 2000), new Movie("movie", "director", 1000, "1"));
    }
}
