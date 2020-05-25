package com.learning.ecart.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import com.learning.ecart.domain.User;
import com.learning.ecart.exception.UserNotFoundException;
import com.learning.ecart.repository.UserRepository;
import com.learning.ecart.util.CreateTestDataUtil;
import com.learning.ecart.validator.UserForm;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

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
	
	/**
	 * check if email exist
	 * @param email
	 * @return true if email exist
	 */
	public boolean existByEmail(String email) {
		return repo.existsByEmail(email);
	}
	
	/**
	 * create a new user to save in the repo 
	 * @param userForm
	 * @return user based on the form 
	 * @throws Exception if form has errors
	 */
	public User initUserFromForm(UserForm userForm) throws Exception {

		User user = new User(CreateTestDataUtil.createObjectId().toHexString(),
								userForm.getFirstName(),
								encoder.encode(userForm.getPassword()),
								"customer",
								userForm.getEmail(),
								userForm.getDob());
		return user;
	}
	
	/**
	 * add a new user on registration
	 * @param user
	 * @return the newly registered user
	 */
	public User addUser(User user) {
		return repo.save(user);
	}
}
