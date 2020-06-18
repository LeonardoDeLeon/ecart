package com.learning.ecart.domain;

import java.util.*;

import org.springframework.stereotype.Component;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class Cart {
	private Map<Product,Integer> products;
}
