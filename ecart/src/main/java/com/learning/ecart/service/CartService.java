package com.learning.ecart.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.ecart.domain.Cart;
import com.learning.ecart.domain.Product;

@Service
public class CartService {
	
	@Autowired
	private ProductService repo;
	
	@Autowired
	private Cart cart;
	
	/**
	 * Write or save the state of a HashMap object as serialized object
	 * @param products
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void writeProductsSer(Map<Product,Integer> products) throws FileNotFoundException, IOException {		
		try (FileOutputStream f = new FileOutputStream(new File("productsHashMap.ser"));
				ObjectOutputStream o = new ObjectOutputStream(f)) {
			
			// write hashmap of item object to new file itemsHashMap.ser
			o.writeObject(products);
		} 
	}
	
	/**
	 * Read back the saved state of a serialized HashMap object
	 * @return HashMap of Product as key and its quantity as value
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public Map<Product,Integer> readProductsSer() throws FileNotFoundException, IOException {
		Map<Product,Integer> products = null;
		
		try (FileInputStream fi = new FileInputStream(new File("productsHashMap.ser"));
				ObjectInputStream oi = new ObjectInputStream(fi)) {
			
			// read hashmap of item object from file cartObj.txt
			try {
				products = (HashMap<Product, Integer>) oi.readObject();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}	
		} 
		return products;
	}
	
	/**
	 * Retrieve the current state of cart
	 * @return HashMap of Products as key and quantity as its value
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public Map<Product,Integer> reloadCurrent() throws FileNotFoundException, IOException {
		// re-load the up-to-date state of items
		Map<Product,Integer> products = readProductsSer();
		
		// if products is null 
		if (products == null) {
			products = new HashMap<>();
		}
		return products;
	}
    
	/**
	 * Update and Reload the cart with the current state of the cart 
	 * @param products
	 * @return cart object containing the current state of the cart
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public Cart reloadCurrent(Map<Product,Integer> products) throws FileNotFoundException, IOException {
		Cart cart = new Cart();
		// load a new state of cart object
		writeProductsSer(products);
		products = null;
		// re-load current state of items
		products = readProductsSer();
		// update the items property of cart
		cart.setProducts(products);
		return cart;
	}
	
	/**
	 * add product item to cart
	 * @param id
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public Cart addToCart(String id) throws FileNotFoundException, IOException {
		int count = 0;

		Product product = repo.findProductById(id);
		
		Map<Product,Integer> products =  new HashMap<>();
		
		try {
			products = reloadCurrent();  
		} catch (Exception e) {
			// if adding to cart for the very first time
			products.put(product, ++count);
			count = 0;
			cart = reloadCurrent(products);
			return cart;
		}
		
		// if cart is not empty
		if (!products.containsKey(product)) { // add new item
			products.put(product,++count);
			count = 0;
			cart = reloadCurrent(products);
			return cart;
		} else { // adding more of the same item
			count += products.get(product);
			products.put(product,++count);
			count = 0;
			cart = reloadCurrent(products);
			return cart;
		}
	}
	
    /**
     * check products in cart
     * @return cart in its current state
     */
    public Cart checkProductsInCart() {
    	Cart cart = new Cart();
    	try {
    		Map<Product,Integer> products = reloadCurrent();
			cart.setProducts(products);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return cart;
    }
    
    /**
     * add product item to cart by id
     * @param id
     */
    public Cart addProductToCart(String id) {
    	try {
			cart = addToCart(id);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return cart;
    }
    
    /**
     * empty the cart
     * @return cart that has been emptied
     * @throws FileNotFoundException
     * @throws IOException
     */
    public Cart emptyCart() throws FileNotFoundException, IOException {
    	Map <Product,Integer> products = reloadCurrent();
    	products.clear();
    	Cart cart = reloadCurrent(products);
    	return cart;
    }
    
    /**
     * remove a product item by id from the cart
     * @param id
     * @return cart with the updated product items
     * @throws FileNotFoundException
     * @throws IOException
     */
    public Cart removeProductFromCart(String id) throws FileNotFoundException, IOException {
    	Product product = repo.findProductById(id);
    	
    	Map<Product,Integer> products = reloadCurrent();
    	
    	int counter = 0;
    	
    	if (products.containsKey(product)) {
    		counter = products.get(product);
    		--counter;
    		if (counter == 0) {
    			products.remove(product);
    		} else {
    			products.replace(product, counter);
    		}	
    	}
    	cart = reloadCurrent(products);
    	return cart;
    }
}
