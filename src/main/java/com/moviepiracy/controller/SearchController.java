package com.moviepiracy.controller;

import com.moviepiracy.model.Movie;
import com.moviepiracy.model.TVShow;
import com.moviepiracy.service.MovieService;
import com.moviepiracy.service.TVShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SearchController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private TVShowService tvShowService;

    @GetMapping("/search/movies")
    public ResponseEntity<List<Movie>> searchMovies(@RequestParam String title) {
        List<Movie> movies = movieService.searchMoviesByTitle(title);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/search/tvshows")
    public ResponseEntity<List<TVShow>> searchTVShows(@RequestParam String title) {
        List<TVShow> tvShows = tvShowService.searchTVShowsByTitle(title);
        return new ResponseEntity<>(tvShows, HttpStatus.OK);
    }
}
