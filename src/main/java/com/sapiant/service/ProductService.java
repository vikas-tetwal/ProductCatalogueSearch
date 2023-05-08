package com.sapiant.service;

import com.sapiant.model.PaginatedProductResponse;
import com.sapiant.model.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    public void saveProduct(Product product);
    public void deleteProduct(int id);

    public PaginatedProductResponse findByFilter(String query, Pageable pageable);

    public long getProductQuantity(String sellerName, int productId);

    public long getInventory(int productId);

    public List<Product> getProductBySku(String sku);

}
