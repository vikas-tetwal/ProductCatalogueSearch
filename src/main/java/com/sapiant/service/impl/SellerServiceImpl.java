package com.sapiant.service.impl;


import com.sapiant.dto.SellerDTO;
import com.sapiant.exception.ProductNotFoundException;
import com.sapiant.entity.ProductEntity;
import com.sapiant.entity.SellerEntity;
import com.sapiant.repository.ProductRepository;
import com.sapiant.repository.SellerRepository;
import com.sapiant.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SellerService")
public class SellerServiceImpl implements SellerService {

	@Autowired
	SellerRepository sellerRepo;
	
	@Autowired
	ProductRepository productRepo;

	public void save(SellerDTO sellerDto) throws ProductNotFoundException {
		List<ProductEntity> productEntity = productRepo.findByProductName(sellerDto.getProductName());
		if(productEntity.size()==0)
			throw new ProductNotFoundException("Product not found in DB!!");
		else
		{
			SellerEntity s = new SellerEntity();
	//		s.setId(sellerDto.getId());
			s.setSellerName(sellerDto.getSellerName());
			s.setQuantity(sellerDto.getQuantity());
			s.setProductId(productEntity.get(0).getProductId());

			sellerRepo.save(s);
		}
	}
	
	public void delete(int id) {
		sellerRepo.deleteById(id);
	}
}
