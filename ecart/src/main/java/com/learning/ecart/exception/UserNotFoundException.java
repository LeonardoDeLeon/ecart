package com.learning.ecart.exception;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String message) {
		// initialize exception class with below message
		super(message); //"UserService.USER_NOT_FOUND"
	}
}
