package com.sapiant.repository;

import com.sapiant.entity.SellerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepository extends CrudRepository<SellerEntity, Integer> {
	List<SellerEntity> findBySellerNameAndProductId(String sellerName, int productId);
	List<SellerEntity> findByProductId(int productId);
}
