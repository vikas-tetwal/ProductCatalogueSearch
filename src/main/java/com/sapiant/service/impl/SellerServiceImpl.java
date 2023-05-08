package com.sapiant.service.impl;


import com.sapiant.dto.SellerDTO;
import com.sapiant.entity.Product;
import com.sapiant.exception.ProductNotFoundException;
import com.sapiant.entity.Seller;
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
		List<Product> product = productRepo.findByProductName(sellerDto.getProductName());
		if(product.size()==0)
			throw new ProductNotFoundException("Product not found in DB!!");
		else
		{
			Seller s = new Seller();
	//		s.setId(sellerDto.getId());
			s.setSellerName(sellerDto.getSellerName());
			s.setQuantity(sellerDto.getQuantity());
			s.setProductId(product.get(0).getProductId());

			sellerRepo.save(s);
		}
	}
	
	public void delete(int id) {
		sellerRepo.deleteById(id);
	}
}
