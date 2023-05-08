package com.sapiant.model;


import com.sapiant.entity.ProductEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PaginatedProductResponse {
    private List<ProductEntity> productsList;
    private Long numberOfItems;
    private int numberOfPages;
}