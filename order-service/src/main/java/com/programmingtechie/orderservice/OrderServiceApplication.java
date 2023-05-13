package com.programmingtechie.orderservice;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import com.programmingtechie.orderservice.dto.InventoryResponse;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	/*
	@Bean
	public CommandLineRunner checkInventory() {
		
		return args -> { WebClient webClient = WebClient.create();
		 InventoryResponse[] inventoryResponseArray = webClient.get()
				.uri("http://localhost:9094/api/inventory?SkuCode=37,17,0")
				.retrieve()
				.bodyToMono(InventoryResponse[].class)
				.block();
		 System.out.println("inventoryResponseArray "+ Arrays.toString(inventoryResponseArray));
			};
	} */
}
