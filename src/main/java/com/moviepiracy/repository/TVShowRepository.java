package com.moviepiracy.repository;

import com.moviepiracy.model.TVShow;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TVShowRepository extends MongoRepository<TVShow, String> {
    // Existing method to find all TV shows
    List<TVShow> findAll();

    // New method to find TV shows by the 'featured' status
    List<TVShow> findByFeatured(boolean featured);

    List<TVShow> findByTitleContaining(String title);
}
