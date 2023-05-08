package com.sapiant.repository;


import com.sapiant.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPaginatedRepository extends PagingAndSortingRepository<ProductEntity, Integer> {
    Page<ProductEntity> findAllByProductNameContains(String name, Pageable pageable);
}
