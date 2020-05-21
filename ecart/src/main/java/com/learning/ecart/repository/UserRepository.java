package com.learning.ecart.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.learning.ecart.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	Optional<User> findByFirstName(String firstName);
	Optional<User> findByEmail(String email);
	boolean existsByEmail(String email);
}
