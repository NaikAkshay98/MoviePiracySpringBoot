package com.moviepiracy.repository;

import com.moviepiracy.model.Banner;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerRepository extends MongoRepository<Banner, String> {
    // You can add custom query methods if needed
}
