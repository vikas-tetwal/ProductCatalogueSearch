package com.sapiant.repository;

import com.sapiant.entity.Seller;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepository extends CrudRepository<Seller, Integer> {
	List<Seller> findBySellerNameAndProductId(String sellerName, int productId);
	List<Seller> findByProductId(int productId);
}
