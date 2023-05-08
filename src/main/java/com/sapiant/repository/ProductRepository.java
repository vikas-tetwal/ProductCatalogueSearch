package com.sapiant.repository;


import com.sapiant.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {

	List<ProductEntity> findByProductName(String name);
	List<ProductEntity> findByBrand(String brand);
	List<ProductEntity> findByColor(String color);
	List<ProductEntity> findByPrice(Double price);
	List<ProductEntity> findBySku(String sku);
	List<ProductEntity> findBySize(int size);
}
