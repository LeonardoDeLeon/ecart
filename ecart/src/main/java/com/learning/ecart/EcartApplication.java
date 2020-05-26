package com.learning.ecart;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.learning.ecart.discount.AppleDiscount;
import com.learning.ecart.discount.BananaDiscount;
import com.learning.ecart.discount.MangoDiscount;
import com.learning.ecart.discount.OrangeDiscount;
import com.learning.ecart.domain.Product;
import com.learning.ecart.domain.User;
import com.learning.ecart.repository.ProductRepository;
import com.learning.ecart.repository.UserRepository;
import com.learning.ecart.util.CreateTestDataUtil;

@SpringBootApplication
public class EcartApplication {
	
	@Autowired
	private BCryptPasswordEncoder encoder;

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
	public CommandLineRunner demoUserData(UserRepository repo) {
		return args -> {
			repo.deleteAll();
			
			// create a couple of users for demo, steve is member only 
			User steve = new User(CreateTestDataUtil.createObjectId().toHexString(),
			           				"Steve",
			           				encoder.encode("pass"),
			           				"customer",
			           				"steve@gmail.com",
			           				"2/2/1940");
			
		    // jacob is member and admin priveledge
			User jacob = new User(CreateTestDataUtil.createObjectId().toHexString(),
					   				"Jacob",
					   				encoder.encode("pass"),
					   				"customer,admin",
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
	
	@Bean
	public CommandLineRunner demoProductData (ProductRepository repo) {
		return args -> {
			repo.deleteAll();
			// create a couple of products for demo
			Product apple = new Product(CreateTestDataUtil.createObjectId().toHexString(),
											"apple",
											"Sometimes red, sometimes green",
											0.45,
											new AppleDiscount(),
											10);
			
			Product banana = new Product(CreateTestDataUtil.createObjectId().toHexString(),
											"banana",
											"Gwen Stefani's favorite fruit",
											0.25,
											new BananaDiscount(),
											20);
			
			Product orange = new Product(CreateTestDataUtil.createObjectId().toHexString(),
											"orange",
											"Use to color white clothing",
											0.35,
											new OrangeDiscount(),
											10);
			
			Product mango = new Product(CreateTestDataUtil.createObjectId().toHexString(),
											"mango",
											"The King of fruits",
											1.09,
											new MangoDiscount(),
											15);
			
			List<Product> products = new ArrayList<>();
			products.add(apple);
			products.add(banana);
			products.add(orange);
			products.add(mango);
			
			repo.saveAll(products);
		};
	}
}
