package com.learning.ecart.domain;

import org.springframework.data.annotation.Id;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	@Id
	private String id;
	private String firstName;
	private String password;
	private String role;
	private String email;
	private String dob;
}
