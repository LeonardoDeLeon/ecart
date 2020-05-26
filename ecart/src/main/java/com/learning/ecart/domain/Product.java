package com.learning.ecart.domain;

import java.io.*;

import org.springframework.data.annotation.Id;

import com.learning.ecart.discount.DiscountStrategy;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product implements Serializable {
	@Id
	private String id;
	private String name;
	private String description;
	private Double price;
	private DiscountStrategy discount;
	private Integer quantity;
	
	/**
	 * override equals to compare product object as key in a hashmap
	 * @return boolean
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null) return false;
		if (getClass() != o.getClass()) return false;
		
		final Product other = (Product) o;
		return true;
	}
	
	/**
	 * override hashcode to a unique property id
	 * @return int id
	 */
	@Override
	public int hashCode() {
		return this.id.hashCode();
	}
}
