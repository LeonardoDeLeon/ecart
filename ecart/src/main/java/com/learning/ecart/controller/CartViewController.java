package com.learning.ecart.controller;

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
}
