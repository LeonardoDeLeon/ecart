package com.learning.ecart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.learning.ecart.repository.ProductRepository;

@Controller
public class ProductListingController {
	
	@Autowired
	ProductRepository repo;
	
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

}
