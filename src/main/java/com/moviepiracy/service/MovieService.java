package com.moviepiracy.service;

import com.moviepiracy.model.Movie;
import com.moviepiracy.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    // New method for featured movies
    public List<Movie> getFeaturedMovies(boolean featured) {
        return movieRepository.findByFeatured(featured);
    }


    public List<Movie> searchMoviesByTitle(String title) {
        return movieRepository.findByTitleContaining(title);
    }

    public Optional<Movie> findMovieById(String id) {
        return movieRepository.findById(id);
    }

    public Optional<Movie> updateMovie(String id, Movie movieDetails) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        if(movieOptional.isPresent()) {
            Movie movieToUpdate = movieOptional.get();
            movieToUpdate.setTitle(movieDetails.getTitle() != null ? movieDetails.getTitle() : movieToUpdate.getTitle());
            movieToUpdate.setRentPrice(movieDetails.getRentPrice() != null ? movieDetails.getRentPrice() : movieToUpdate.getRentPrice());
            // Update other fields as necessary
            movieRepository.save(movieToUpdate);
            return Optional.of(movieToUpdate);
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteMovieById(String id) {
        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }
}
