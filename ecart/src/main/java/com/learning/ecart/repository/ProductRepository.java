package com.learning.ecart.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.learning.ecart.domain.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
	Product findByName(String name);
}
