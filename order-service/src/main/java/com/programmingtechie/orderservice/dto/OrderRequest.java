package com.programmingtechie.orderservice.dto;

import java.util.List;

import com.programmingtechie.orderservice.model.OrderLineItems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

	private List<OrderLineItemsDto> orderLineItems;
}
