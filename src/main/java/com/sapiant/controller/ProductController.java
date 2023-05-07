package com.sapiant.controller;

import com.sapiant.model.Product;
import com.sapiant.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

	@Autowired
	ProductService service;
	
	@PostMapping("/product/save")
	public void saveProduct(@RequestBody Product product) {
		service.saveProduct(product);
	}
	
	@DeleteMapping("/product/delete/{id}")
	public void deleteProduct(@PathVariable int id) {
		service.deleteProduct(id);
	}
	
	@GetMapping("/product/getByBrand/{brand}")
	public List<Product> getProductByBrand(@PathVariable String brand) {
		List<Product> result = service.getProductByBrand(brand);
		
		return result;
	}
	
	@GetMapping("/product/getByColor/{color}")
	public List<Product> getProductByColor(@PathVariable String color) {
		List<Product> result = service.getProductByColor(color);
		
		return result;
	}
	
	@GetMapping("/product/getBySize/{size}")
	public List<Product> getProductBySize(@PathVariable int size) {
		List<Product> result = service.getProductBySize(size);

		return result;
	}
	
	@GetMapping("/product/getByPrice/{price}")
	public List<Product> getProductByPrice(@PathVariable Double price) {
		List<Product> result = service.getProductByPrice(price);
		
		return result;
	}
	
	@GetMapping("/product/getBySku/{sku}")
	public List<Product> getProductBySku(@PathVariable String sku) {
		List<Product> result = service.getProductBySku(sku);
		
		return result;
	}
	
	@GetMapping("/product/{sellerName}/{productId}")
	public long getQuantityBySeller(@PathVariable String sellerName, @PathVariable int productId) {
		long count = service.getProductQuantity(sellerName,productId);
		
		return count;
	}
	
	@GetMapping("product/inventory/{productId}")
	public long getInventory(@PathVariable int productId) {
		long count = 0;
		count = service.getInventory(productId);
		return count;
	}
}
