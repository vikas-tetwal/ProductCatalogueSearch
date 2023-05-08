package com.sapiant.service;

import com.sapiant.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ProductService {

    public void saveProduct(Product product);
    public void deleteProduct(int id);

    public Page<Product> findByFilter(int page, int size , String sortBy, String sortType, Map<String, String> filters);

    public long getProductQuantity(String sellerName, int productId);

    public long getInventory(int productId);

    public List<Product> getProductBySku(String sku);

}
