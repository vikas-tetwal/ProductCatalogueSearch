package com.sapiant.service;

import com.sapiant.entity.ProductEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ProductService {

    public void saveProduct(ProductEntity productEntity);
    public void deleteProduct(int id);

    public Page<ProductEntity> findByFilter(int page, int size , String sortBy, String sortType, Map<String, String> filters);

    public long getProductQuantity(String sellerName, int productId);

    public long getInventory(int productId);

    public List<ProductEntity> getProductBySku(String sku);

}
