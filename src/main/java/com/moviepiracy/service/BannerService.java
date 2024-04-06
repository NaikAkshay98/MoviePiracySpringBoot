package com.moviepiracy.service;

import com.moviepiracy.model.Banner;
import com.moviepiracy.repository.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BannerService {

    @Autowired
    private BannerRepository bannerRepository;

    public List<Banner> findAllBanners() {
        return bannerRepository.findAll();
    }

    public Optional<Banner> findBannerById(String id) {
        return bannerRepository.findById(id);
    }

    public Banner saveBanner(Banner banner) {
        return bannerRepository.save(banner);
    }

    // Add other methods as needed
}
