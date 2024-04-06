package com.moviepiracy.repository;

import com.moviepiracy.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {
    // This method fetches all movies from the database.
    List<Movie> findAll();

    // This method fetches movies based on the 'featured' flag.
    List<Movie> findByFeatured(boolean featured);

    List<Movie> findByTitleContaining(String title);
}
