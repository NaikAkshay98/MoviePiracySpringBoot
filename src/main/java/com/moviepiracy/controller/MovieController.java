package com.moviepiracy.controller;

import com.moviepiracy.model.Movie;
import com.moviepiracy.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping; // Import for PutMapping
import org.springframework.web.bind.annotation.RequestBody; // Import for RequestBody
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;
import java.util.Optional; // Import statement for Optional

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/featured")
    public ResponseEntity<List<Movie>> getFeaturedMovies(@RequestParam(required = false, defaultValue = "true") boolean featured) {
        List<Movie> movies = movieService.getFeaturedMovies(featured);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable String id) {
        Optional<Movie> movieOpt = movieService.findMovieById(id);
        if (movieOpt.isPresent()) {
            return ResponseEntity.ok(movieOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found with ID: " + id);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable String id, @RequestBody Movie movie) {
        Optional<Movie> updatedMovie = movieService.updateMovie(id, movie);
        if(updatedMovie.isPresent()) {
            return new ResponseEntity<>(updatedMovie.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Movie not found with ID: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable String id) {
        boolean deleted = movieService.deleteMovieById(id);
        if (deleted) {
            return ResponseEntity.ok().body("Movie with ID: " + id + " was deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found with ID: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        Movie newMovie = movieService.addMovie(movie);
        return new ResponseEntity<>(newMovie, HttpStatus.CREATED);
    }
}
