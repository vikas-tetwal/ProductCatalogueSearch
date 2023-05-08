package com.sapiant.service.impl;


import com.sapiant.entity.ProductEntity;
import com.sapiant.entity.SellerEntity;
import com.sapiant.repository.ProductPaginatedRepository;
import com.sapiant.repository.ProductRepository;
import com.sapiant.repository.SellerRepository;
import com.sapiant.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("ProductService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepo;

	@Autowired
	ProductPaginatedRepository paginatedRepository;
	
	@Autowired
	SellerRepository sellerRepo;
	
	public void saveProduct(ProductEntity productEntity) {
		productRepo.save(productEntity);
	}
	
	public void deleteProduct(int id) {
		productRepo.deleteById(id);
	}

	@Autowired
	EntityManager entityManager;
	@Autowired
	CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

	public Page<ProductEntity> findByFilter(int page, int size , String sortBy, String sortType, Map<String, String> filters){
		List<ProductEntity> result;

		CriteriaQuery<ProductEntity> criteriaQuery = criteriaBuilder.createQuery(ProductEntity.class);

		Root<ProductEntity> orderRoot = criteriaQuery.from(ProductEntity.class);
		criteriaQuery.select(orderRoot);
			Predicate predicate = getPredicate(criteriaQuery, orderRoot, filters, false);
		criteriaQuery.where(predicate);
		if(Sort.Direction.ASC.name().equals(sortType)){
			criteriaQuery.orderBy(criteriaBuilder.asc(orderRoot.get(sortBy)));
		}else {
			criteriaQuery.orderBy(criteriaBuilder.desc(orderRoot.get(sortBy)));
		}

		TypedQuery<ProductEntity> typedQuery = entityManager.createQuery(criteriaQuery);
		typedQuery.setFirstResult(page * size);
		typedQuery.setMaxResults(size);
		Pageable pageable = getPageable(page, size, sortBy, sortType);
		long orderCount = getOrderCount(predicate);
		return new PageImpl<>(typedQuery.getResultList(), pageable, orderCount);
	}

	private long getOrderCount(Predicate predicate) {
		CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
		Root<ProductEntity> countRoot = countQuery.from(ProductEntity.class);
		countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
		return entityManager.createQuery(countQuery).getSingleResult();
	}

	private Pageable getPageable(int page, int size, String sortBy, String sortType) {
		Sort sort = Sort.by(Sort.Direction.fromString(sortType), sortBy);
		return PageRequest.of(page, size, sort);
	}

	private Predicate getPredicate(CriteriaQuery<ProductEntity> criteriaQuery, Root<ProductEntity> orderRoot, Map<String, String> filters, Boolean includeDeleted) {
		List<Predicate> predicateList = new ArrayList<>();
		if(!includeDeleted){
			predicateList.add(criteriaBuilder.equal(orderRoot.get(ProductEntity.DELETED), false));
		}
		if(filters.containsKey(ProductEntity.BRAND)){
			predicateList.add(criteriaBuilder.equal(orderRoot.get(ProductEntity.BRAND), filters.get(ProductEntity.BRAND)));
		}
		if(filters.containsKey(ProductEntity.SIZE)){
			predicateList.add(criteriaBuilder.equal(orderRoot.get(ProductEntity.SIZE), filters.get(ProductEntity.SIZE)));
		}
		if(filters.containsKey(ProductEntity.COLOR)){
			predicateList.add(criteriaBuilder.equal(orderRoot.get(ProductEntity.COLOR), filters.get(ProductEntity.COLOR)));
		}

		return criteriaBuilder.and(predicateList.stream().toArray(Predicate[] :: new));
	}

	public List<ProductEntity> getProductByColor(String color){
		List<ProductEntity> result;
		result = productRepo.findByColor(color);
		return result;
	}
	
	public List<ProductEntity> getProductBySize(int size){
		List<ProductEntity> result;
		result = productRepo.findBySize(size);
		return result;
	}
	
	public List<ProductEntity> getProductBySku(String sku){
		List<ProductEntity> result;
		result = productRepo.findBySku(sku);
		return result;
	}
	
	public List<ProductEntity> getProductByPrice(Double price){
		List<ProductEntity> result;
		result = productRepo.findByPrice(price);
		return result;
	}
	
	public long getProductQuantity(String sellerName, int productId) {
		List<SellerEntity> sellerEntity = sellerRepo.findBySellerNameAndProductId(sellerName, productId);
		long count = 0;
		count = sellerEntity.stream().map(s->s.getQuantity()).reduce(0,Integer::sum);
		return count;
	}
	
	public long getInventory(int productId) {
		long count = 0;
		
		List<SellerEntity> sellerEntity = sellerRepo.findByProductId(productId);
		count = sellerEntity.stream().map(s->s.getQuantity()).reduce(0, Integer::sum);
		return count;
	}
}
