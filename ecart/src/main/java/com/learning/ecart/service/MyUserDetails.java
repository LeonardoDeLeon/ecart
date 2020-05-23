package com.learning.ecart.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.learning.ecart.domain.User;
import com.learning.ecart.exception.UserNotFoundException;
import com.learning.ecart.repository.UserRepository;

public class MyUserDetails implements UserDetailsService {

	@Autowired
	private UserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {
		email.toLowerCase();
		System.out.println("email = "+email);
		Optional <User> optional = repo.findByEmail(email);
		optional.orElseThrow(() -> new UserNotFoundException("User not found: "+email));
		return optional.map(UserPrincipal::new).get();
	}
}
