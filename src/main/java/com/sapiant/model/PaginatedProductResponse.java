package com.sapiant.model;


import com.sapiant.entity.Product;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PaginatedProductResponse {
    private List<Product> productsList;
    private Long numberOfItems;
    private int numberOfPages;
}