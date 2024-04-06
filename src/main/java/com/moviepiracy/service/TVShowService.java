package com.moviepiracy.service;

import com.moviepiracy.model.TVShow;
import com.moviepiracy.repository.TVShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class TVShowService {

    @Autowired
    private TVShowRepository tvShowRepository;

    public List<TVShow> getAllTVShows() {
        return tvShowRepository.findAll();
    }

    // New method to get featured TV shows
    public List<TVShow> getFeaturedTVShows(boolean featured) {
        return tvShowRepository.findByFeatured(featured);
    }

    public List<TVShow> searchTVShowsByTitle(String title) {
        return tvShowRepository.findByTitleContaining(title);
    }

    public Optional<TVShow> findTVShowById(String id) {
        return tvShowRepository.findById(id);
    }

    public Optional<TVShow> updateTVShow(String id, TVShow tvShowDetails) {
        Optional<TVShow> tvShowOptional = tvShowRepository.findById(id);
        if(tvShowOptional.isPresent()) {
            TVShow tvShowToUpdate = tvShowOptional.get();
            // Update fields as necessary, e.g., title, rentPrice
            tvShowToUpdate.setTitle(tvShowDetails.getTitle() != null ? tvShowDetails.getTitle() : tvShowToUpdate.getTitle());
            tvShowToUpdate.setRentPrice(tvShowDetails.getRentPrice() != null ? tvShowDetails.getRentPrice() : tvShowToUpdate.getRentPrice());
            // Additional fields here...

            tvShowRepository.save(tvShowToUpdate);
            return Optional.of(tvShowToUpdate);
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteTVShowById(String id) {
        if (tvShowRepository.existsById(id)) {
            tvShowRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public TVShow addTVShow(TVShow tvShow) {
        return tvShowRepository.save(tvShow);
    }
}
