package com.sapiant.service;

import com.sapiant.model.Product;

import java.util.List;

public interface ProductService {

    public void saveProduct(Product product);
    public void deleteProduct(int id);

    public List<Product> getProductByBrand(String brand);

    public List<Product> getProductByColor(String color);

    public List<Product> getProductBySize(int size);

    public List<Product> getProductBySku(String sku);

    public List<Product> getProductByPrice(Double price);

    public long getProductQuantity(String sellerName, int productId);

    public long getInventory(int productId);

}
