package com.programmingtechie.orderservice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import com.programmingtechie.orderservice.dto.InventoryResponse;
import com.programmingtechie.orderservice.dto.OrderLineItemsDto;
import com.programmingtechie.orderservice.dto.OrderRequest;
import com.programmingtechie.orderservice.model.Order;
import com.programmingtechie.orderservice.model.OrderLineItems;
import com.programmingtechie.orderservice.repository.OrderRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private WebClient webClient;
	
	public void placeOrder(OrderRequest orderRequest) {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		
		
		List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItems()
		.stream()
		.map(this::mapToDto)
		.toList();
		order.setOrderLineItemsList(orderLineItems); 
		
		List<String> skuCodes = order.getOrderLineItemsList().stream()
		.map(OrderLineItems::getSkuCode)
		.toList();

			
		InventoryResponse[] inventoryResponseArray = WebClient.create().get()
		.uri("http://localhost:9094/api/inventory", uriBuilder -> uriBuilder.queryParam("SkuCode", skuCodes).build())
		.retrieve()
		.bodyToMono(InventoryResponse[].class)
		.block();
		
		boolean allProductsinStock = Arrays.stream(inventoryResponseArray)
				.allMatch(InventoryResponse::isStock); 
		if (allProductsinStock) {
			orderRepository.save(order);
		}else {
			throw new IllegalArgumentException("Product is not in stock");
		}
		//orderRepository.save(order);
	}
	
	private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {  
		OrderLineItems orderLineItems = new OrderLineItems();
		orderLineItems.setPrice(orderLineItemsDto.getPrice());
		orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
		orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());  
		return orderLineItems;
	}
}
