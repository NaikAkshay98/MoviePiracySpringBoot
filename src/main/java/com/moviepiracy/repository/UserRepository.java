package com.moviepiracy.repository;

import com.moviepiracy.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface UserRepository extends MongoRepository<User, String> {
    // Additional methods, if needed, e.g., findByEmail

    Optional<User> findByEmail(String email);
}
