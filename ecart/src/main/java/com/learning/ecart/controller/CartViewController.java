package com.learning.ecart.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.learning.ecart.service.CartService;

@Controller
public class CartViewController {

	@Autowired
	CartService service;
	
	@GetMapping("/cartview")
	public String cartView(Model model) {
		model.addAttribute("carts",service.checkProductsInCart());
		return "cartview";
	}
	
	@GetMapping("/emptycart")
	public String emptyCart(Model model) throws FileNotFoundException, IOException {
		model.addAttribute("carts",service.emptyCart());
		return "cartview";
	}
}
