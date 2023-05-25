package com.sapiant.service;

import com.sapiant.entity.Product;

import java.util.List;

public interface ProductService {

    public void saveProduct(Product product);
    public void deleteProduct(int id);

    public List<Product> findByFilter(int page, int size , String sortBy, String sortType, String filters);

    public long getProductQuantity(String sellerName, int productId);

    public long getInventory(int productId);

    public List<Product> getProductBySku(String sku);

}
