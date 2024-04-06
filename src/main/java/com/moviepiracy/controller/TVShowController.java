package com.moviepiracy.controller;

import com.moviepiracy.model.TVShow;
import com.moviepiracy.service.TVShowService;
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
import java.util.Optional; // Make sure this import is present

@RestController
@RequestMapping("/api/tvshows")
public class TVShowController {

    @Autowired
    private TVShowService tvShowService;

    @GetMapping
    public ResponseEntity<List<TVShow>> getAllTVShows() {
        List<TVShow> tvShows = tvShowService.getAllTVShows();
        return new ResponseEntity<>(tvShows, HttpStatus.OK);
    }

    @GetMapping("/featured")
    public ResponseEntity<List<TVShow>> getFeaturedTVShows(@RequestParam(required = false, defaultValue = "true") boolean featured) {
        List<TVShow> tvShows = tvShowService.getFeaturedTVShows(featured);
        return new ResponseEntity<>(tvShows, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTVShowById(@PathVariable String id) {
        Optional<TVShow> tvShowOpt = tvShowService.findTVShowById(id);
        if (tvShowOpt.isPresent()) {
            return ResponseEntity.ok(tvShowOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("TV Show not found with ID: " + id);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTVShow(@PathVariable String id, @RequestBody TVShow tvShow) {
        Optional<TVShow> updatedTVShow = tvShowService.updateTVShow(id, tvShow);
        if(updatedTVShow.isPresent()) {
            return ResponseEntity.ok(updatedTVShow.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("TV Show not found with ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTVShow(@PathVariable String id) {
        boolean deleted = tvShowService.deleteTVShowById(id);
        if (deleted) {
            return ResponseEntity.ok().body("TV Show with ID: " + id + " was deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("TV Show not found with ID: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<TVShow> addTVShow(@RequestBody TVShow tvShow) {
        TVShow newTVShow = tvShowService.addTVShow(tvShow);
        return new ResponseEntity<>(newTVShow, HttpStatus.CREATED);
    }
}
