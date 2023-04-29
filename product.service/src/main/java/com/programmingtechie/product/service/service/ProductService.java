package com.programmingtechie.product.service.service;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.programmingtechie.product.service.dto.ProductRequest;
import com.programmingtechie.product.service.dto.ProductResponse;
import com.programmingtechie.product.service.model.Product;
import com.programmingtechie.product.service.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public void createProduct(@RequestBody ProductRequest productRequest) {
		Product product = Product.builder()
				.name(productRequest.getName())
				.description(productRequest.getDescription())
				.price(productRequest.getPrice())
				.build();
		productRepository.save(product);
		
	} 

	public List<ProductResponse> getAllProducts() {

		List<Product> products = productRepository.findAll();
		return products.stream().map(this :: mapToProductResponse).toList(); 
		
	}
	private ProductResponse mapToProductResponse(Product product) {
		return ProductResponse.builder()
				.id(product.getId())
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice())
				.build();
	}
}
	
