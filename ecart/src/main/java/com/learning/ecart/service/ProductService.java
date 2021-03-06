package com.learning.ecart.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.ecart.domain.Product;
import com.learning.ecart.exception.ProductNotFoundException;
import com.learning.ecart.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository repo;
	
	/**
	 * retrieve all products in the repo
	 * @return List of product
	 */
	public List<Product> fetchAll() {
		return repo.findAll();
	}
	
	/**
	 * retrieve a product by name
	 * @param name
	 * @return Product 
	 */
	public Product fetchByName(String name) {
		return repo.findByName(name);
	}

    /**
     * retrieve optional product object
     * @param id
     * @return Optional Product object
     */
    public Product findProductById(String id) {
    	Optional<Product> optional = repo.findById(id);
    	return optional.orElseThrow(() ->
    	    new ProductNotFoundException("ProductService.PRODUCT_NOT_FOUND, id = "+id));
    }
}
