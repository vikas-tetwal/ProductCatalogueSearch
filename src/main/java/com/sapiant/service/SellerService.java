package com.sapiant.service;

import com.sapiant.dto.SellerDTO;
import com.sapiant.exception.ProductNotFoundException;

public interface SellerService {

    public void save(SellerDTO sellerDto) throws ProductNotFoundException;

    public void delete(int id);
}
