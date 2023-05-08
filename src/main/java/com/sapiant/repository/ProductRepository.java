package com.sapiant.repository;


import com.sapiant.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

	List<Product> findByProductName(String name);
	List<Product> findByBrand(String brand);
	List<Product> findByColor(String color);
	List<Product> findByPrice(Double price);
	List<Product> findBySku(String sku);
	List<Product> findBySize(int size);
}
