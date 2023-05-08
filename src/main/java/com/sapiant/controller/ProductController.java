package com.sapiant.controller;

import com.sapiant.entity.Product;
import com.sapiant.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
	public Page<Product> getProductByBrand(@RequestParam("page") int page,
										   @RequestParam("size") int size,
										   @RequestParam("sortBy") String sortBy,
										   @RequestParam("sortType") String sortType,
										   @RequestParam("") Map<String, String> filters,
										   Pageable pageable) {
		log.info("Inside /product/delete/{id}");
		Page<Product> result = service.findByFilter(page,size,sortBy,sortType, filters);
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
