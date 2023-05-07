package com.sapiant.controller;


import com.sapiant.dto.SellerDTO;
import com.sapiant.exception.ProductNotFoundException;
import com.sapiant.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SellerController {

	@Autowired
	SellerService sellerService;
	
	@PostMapping("/seller/save")
	public void save(@RequestBody SellerDTO seller) throws ProductNotFoundException {
		sellerService.save(seller);
	}
	
	@DeleteMapping("/seller/delete/{id}")
	public void delete(@PathVariable int id) {
		sellerService.delete(id);
	}
	
}
