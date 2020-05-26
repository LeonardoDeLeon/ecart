package com.learning.ecart.discount;

import java.io.*;
import java.util.*;

public class BananaDiscount implements DiscountStrategy, Serializable {
	//private static final Logger logger = LoggerFactory.getLogger(AppleDiscount.class);
	private static final long serialVersionUID = 1L;	
	private Integer discount;
	
	/**
	 * no-arg constructor to call init() to initialize discount property
	 */
	public BananaDiscount() {
		try {
			//logger.info("default constructor to initialize apple discount");
			init();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * retrieve the discount amount for apple item product
	 */
	@Override
	public Integer getDiscount() {
		//logger.info("apple discount = "+discount);
		return discount;  
	}
	
	/**
	 * initialize discount property from resources/discount.properties 
	 * @throws IOException
	 */
	public void init() throws IOException {
		
		try (InputStream input = AppleDiscount.class.getClassLoader().getResourceAsStream("discount.properties")) {
	          Properties prop = new Properties();
	          prop.load(input);
	          discount = Integer.valueOf(prop.getProperty("BANANA_DISCOUNT"));
	          //logger.info("afta init() to prop file, apple discount = "+discount);
		} catch (IOException io) {
			io.printStackTrace();
			throw io;
		}
	}
}
