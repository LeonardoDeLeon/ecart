package com.learning.ecart.service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.learning.ecart.domain.User;

public class UserPrincipal implements UserDetails {

	private String password;
	private String username;
	private List<GrantedAuthority> authorities;
	
	/**
	 * parametized constructor for returning the user principal
	 * @param user
	 */
	public UserPrincipal(User user) {
		this.password = user.getPassword();
		this.username = user.getEmail();
		this.authorities = Arrays.stream(user.getRole().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
