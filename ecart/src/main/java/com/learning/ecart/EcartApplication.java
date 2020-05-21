package com.learning.ecart;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.learning.ecart.domain.User;
import com.learning.ecart.repository.UserRepository;
import com.learning.ecart.util.CreateTestDataUtil;

@SpringBootApplication
public class EcartApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcartApplication.class, args);
	}
	
	/**
	 * this registered CommandLineRunner bean will execute the lambda 
	 * which clears the repo and then create and save two new users in 
	 * the repository for demo  purpose
	 * @param repo
	 * @return
	 */
	@Bean
	public CommandLineRunner demoData(UserRepository repo) {
		return args -> {
			repo.deleteAll();
			
			//create  a couple of users for demo
			User steve = new User(CreateTestDataUtil.createObjectId().toHexString(),
			           				"Steve",
			           				"pass",
			           				"customer",
			           				"steve@gmail.com",
			           				"2/2/1940");
			           
			User jacob = new User(CreateTestDataUtil.createObjectId().toHexString(),
					   				"Jacob",
					   				"pass",
					   				"customer",
					   				"jacob@gmail.com",
					   				"2/2/1980");
			
			// add user to list of users
			List<User> users = new ArrayList<>();	
			users.add(steve);
			users.add(jacob);
			
			// save them in the repository
			repo.saveAll(users);		
		};
	}
}
