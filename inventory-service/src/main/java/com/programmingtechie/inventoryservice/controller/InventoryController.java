package com.programmingtechie.inventoryservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.programmingtechie.inventoryservice.dto.InventoryResponse;
import com.programmingtechie.inventoryservice.service.InventoryService;

import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

	private final InventoryService inventoryService;
	
	/*
	@GetMapping("/{skucode1}")
	@ResponseStatus(HttpStatus.OK)
	public boolean isInStock1(@PathVariable("skucode") String skucode) {
		return inventoryService.isInStock(skucode);
	} */
	
		
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<InventoryResponse> isInStock(@RequestParam List<String>  SkuCode) {
		return inventoryService.isInStock(SkuCode);
	}
}
