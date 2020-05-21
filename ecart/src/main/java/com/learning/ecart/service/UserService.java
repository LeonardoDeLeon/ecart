package com.learning.ecart.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.ecart.domain.User;
import com.learning.ecart.exception.UserNotFoundException;
import com.learning.ecart.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	/**
	 * retrieve user by email
	 * @param email
	 * @return user 
	 */
	public User findByEMail(String email) {
		Optional<User> opt = repo.findByEmail(email); 
		opt.orElseThrow(()->new UserNotFoundException("Cannot find user with email: "+email));
	    return opt.get();
	}
}
