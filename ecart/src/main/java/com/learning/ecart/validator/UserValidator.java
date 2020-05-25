package com.learning.ecart.validator;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.learning.ecart.service.UserService;

@Component
public class UserValidator implements Validator {

	private EmailValidator emailValidator = EmailValidator.getInstance();
	
	@Autowired
	private UserService userService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == UserForm.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserForm userForm = (UserForm) target;
		
		// form fields must not be blank
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.userForm.firstName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.userForm.email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.userForm.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.userForm.confirmPassword");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dob", "NotEmpty.userForm.dob");
		
		// if email is not empty
		if (userForm.getEmail() != "") {
			// first, check if email is in valid format		
			if (!this.emailValidator.isValid(userForm.getEmail())) {
				// invalid email
				errors.rejectValue("email","Pattern.userForm.email");
			} else {
				// if email is valid, next check if it exist 	
				if (userService.existByEmail(userForm.getEmail())) {
					// reject value if email exist
					errors.rejectValue("email", "Duplicate.userForm.email");
				}
			}	
		} 
		
		// if confirmPassword is not empty
		if (userForm.getConfirmPassword() != "") {
			// check the passwords for equality
	        if (!userForm.getConfirmPassword().equals(userForm.getPassword())) {
	        	// reject the value if they are not equal
	            errors.rejectValue("confirmPassword", "Match.userForm.confirmPassword");
	        }
		}

        
		// if dob field is not empty
		if (userForm.getDob() != "") {
			int age = 0;
			try {
				// calculate the age
				age = calculateAge(userForm.getDob(),LocalDate.now());
				System.out.println("age is "+age);
				if (age < 18) {
					// age is less than 18
					errors.rejectValue("dob","Under18.userForm.dob");
				}
			} catch (DateTimeException e) {
				// date provided is in not of the specified format: d/MM/yyyy
				e.printStackTrace();
				errors.rejectValue("dob", "BadDateFormat.userForm.dob");
			}
		}
	}

	/**
	 * calculate age base on input stream
	 * @param date
	 * @param currentDate
	 * @return age in int
	 */
    public int calculateAge(String date, LocalDate currentDate) {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
    	//convert String to LocalDate
    	date = date == "" ? "1/02/2000" : date;
    	LocalDate birthDate = LocalDate.parse(date, formatter);
    	
    	if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }
	
}
