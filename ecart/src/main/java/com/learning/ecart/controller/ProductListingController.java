package com.learning.ecart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.learning.ecart.repository.ProductRepository;
import com.learning.ecart.service.CartService;

@Controller
public class ProductListingController {
	
	@Autowired
	ProductRepository repo;
	
	@Autowired
	CartService service;
	
	/**
	 * List of all the items in shopping app 
	 * @param model
	 * @return String uri of product listing 
	 */
	@GetMapping("/productListing")
	public String productListing(Model model) {
		model.addAttribute("products", repo.findAll());
		return "productListing";
	}	
	
	/**
	 * add item to cart
	 * @param id
	 * @param model
	 * @return String cartview 
	 */
	@GetMapping("/productListing/add/{id}")
	public String addProduct(@PathVariable String id, Model model) {
		model.addAttribute("carts",service.addProductToCart(id));
		return "cartview";
	}

}
