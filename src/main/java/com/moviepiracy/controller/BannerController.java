package com.moviepiracy.controller;

import com.moviepiracy.model.Banner;
import com.moviepiracy.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/banners")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @GetMapping
    public ResponseEntity<List<Banner>> getAllBanners() {
        List<Banner> banners = bannerService.findAllBanners();
        return new ResponseEntity<>(banners, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Banner> getBannerById(@PathVariable String id) {
        return bannerService.findBannerById(id)
                .map(banner -> new ResponseEntity<>(banner, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Banner> createBanner(@RequestBody Banner banner) {
        Banner savedBanner = bannerService.saveBanner(banner);
        return new ResponseEntity<>(savedBanner, HttpStatus.CREATED);
    }

    // Add other endpoint handlers as needed
}
