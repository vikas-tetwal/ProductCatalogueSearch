package com.sapiant.controller;

import com.sapiant.model.PaginatedProductResponse;
import com.sapiant.model.Product;
import com.sapiant.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ProductController {

	@Autowired
	ProductService service;
	
	@PostMapping("product/save")
	public void saveProduct(@RequestBody Product product) {
		log.info("Inside /product/save ");
		service.saveProduct(product);
	}
	
	@DeleteMapping("product/delete/{id}")
	public void deleteProduct(@PathVariable int id) {
		log.info("Inside /product/delete/{id}");
		service.deleteProduct(id);
	}
	
	@GetMapping("product/search")
	public PaginatedProductResponse getProductByBrand(@RequestParam("query") String query,Pageable pageable) {
		log.info("Inside /product/delete/{id}");
		PaginatedProductResponse result = service.findByFilter(query, pageable);
		
		return result;
	}

	@GetMapping("product/{sellerName}/{productId}")
	public long getQuantityBySeller(@PathVariable String sellerName, @PathVariable int productId) {
		log.info("Inside /product/{sellerName}/{productId}");
		long count = service.getProductQuantity(sellerName,productId);
		
		return count;
	}
	
	@GetMapping("product/inventory/{productId}")
	public long getInventory(@PathVariable int productId) {
		log.info("Inside product/inventory/{productId}");
		long count = 0;
		count = service.getInventory(productId);
		return count;
	}

	@GetMapping("product/getBySku/{sku}")
	public ResponseEntity<List<Product>> getProductBySku(@PathVariable String sku) {
		log.info("Inside /product/getBySku/{sku}");
		return new ResponseEntity<>(service.getProductBySku(sku), HttpStatus.OK);
	}
}
