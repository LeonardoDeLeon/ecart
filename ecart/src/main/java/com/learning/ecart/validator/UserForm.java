package com.learning.ecart.validator;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserForm {
	private String firstName;
	private String email;
	private String password;
	private String confirmPassword;
	private String dob;
}
