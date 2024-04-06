package com.moviepiracy.model;

import java.util.List;

public class SearchResults {
    private List<Movie> movies;
    private List<TVShow> tvShows;

    // Constructor
    public SearchResults(List<Movie> movies, List<TVShow> tvShows) {
        this.movies = movies;
        this.tvShows = tvShows;
    }

    // Getters
    public List<Movie> getMovies() {
        return movies;
    }

    public List<TVShow> getTvShows() {
        return tvShows;
    }

    // Setters
    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public void setTvShows(List<TVShow> tvShows) {
        this.tvShows = tvShows;
    }
}
