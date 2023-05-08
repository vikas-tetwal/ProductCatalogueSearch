package com.sapiant.repository;


import com.sapiant.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPaginatedRepository extends PagingAndSortingRepository<Product, Integer> {
    Page<Product> findAllByProductNameContains(String name, Pageable pageable);
}
