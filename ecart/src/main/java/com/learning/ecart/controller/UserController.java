package com.learning.ecart.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.learning.ecart.domain.User;
import com.learning.ecart.repository.UserRepository;
import com.learning.ecart.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService service;
	
	@Autowired
	UserRepository repo;
	
	/**
	 * retrieve all users from repository
	 * @return List of users
	 */
	@GetMapping("/users")
	public List<User> fetchAll() {
		return repo.findAll();
	}
	
	@GetMapping("/users/email/{email}")
	public User fetchByEmail(@PathVariable String email) {
		return service.findByEMail(email);
	}
}
