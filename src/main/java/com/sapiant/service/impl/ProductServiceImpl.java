package com.sapiant.service.impl;


import com.sapiant.model.PaginatedProductResponse;
import com.sapiant.model.Product;
import com.sapiant.model.Seller;
import com.sapiant.repository.ProductPaginatedRepository;
import com.sapiant.repository.ProductRepository;
import com.sapiant.repository.SellerRepository;
import com.sapiant.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ProductService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepo;

	@Autowired
	ProductPaginatedRepository paginatedRepository;
	
	@Autowired
	SellerRepository sellerRepo;
	
	public void saveProduct(Product product) {
		productRepo.save(product);
	}
	
	public void deleteProduct(int id) {
		productRepo.deleteById(id);
	}


	public PaginatedProductResponse findByFilter(String query, Pageable pageable){
		List<Product> result;
		Page<Product> books = paginatedRepository.findAllByProductNameContains(query, pageable);
		return PaginatedProductResponse.builder()
				.numberOfItems(books.getTotalElements())
				.numberOfPages(books.getTotalPages())
				.productsList(books.getContent())
				.build();
	}
	
	public List<Product> getProductByColor(String color){
		List<Product> result;
		result = productRepo.findByColor(color);
		return result;
	}
	
	public List<Product> getProductBySize(int size){
		List<Product> result;
		result = productRepo.findBySize(size);
		return result;
	}
	
	public List<Product> getProductBySku(String sku){
		List<Product> result;
		result = productRepo.findBySku(sku);
		return result;
	}
	
	public List<Product> getProductByPrice(Double price){
		List<Product> result;
		result = productRepo.findByPrice(price);
		return result;
	}
	
	public long getProductQuantity(String sellerName, int productId) {
		List<Seller> seller = sellerRepo.findBySellerNameAndProductId(sellerName, productId);
		long count = 0;
		count = seller.stream().map(s->s.getQuantity()).reduce(0,Integer::sum);
		return count;
	}
	
	public long getInventory(int productId) {
		long count = 0;
		
		List<Seller> seller = sellerRepo.findByProductId(productId);
		count = seller.stream().map(s->s.getQuantity()).reduce(0, Integer::sum);
		return count;
	}
}
