package com.learning.ecart.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.ecart.domain.Product;
import com.learning.ecart.repository.ProductRepository;
import com.learning.ecart.service.ProductService;

@RestController
@RequestMapping("products")
public class ProductController {
	
	@Autowired
	ProductRepository repo;
	
	@Autowired
	ProductService service;
	
	@GetMapping("/catalog")
	public List<Product> fetchAll() {
		return repo.findAll();
	}
	
	@GetMapping("/catalog/{name}")
	public Product fetchByName(@PathVariable String name) {
		return service.fetchByName(name);
	}
}
