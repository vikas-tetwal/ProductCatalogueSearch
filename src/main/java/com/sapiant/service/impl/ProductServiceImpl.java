package com.sapiant.service.impl;


import com.sapiant.entity.Product;
import com.sapiant.entity.Seller;
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
	public ProductServiceImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
		this.criteriaBuilder = entityManager.getCriteriaBuilder();
	}

	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	SellerRepository sellerRepo;
	
	public void saveProduct(Product product) {
		productRepo.save(product);
	}
	
	public void deleteProduct(int id) {
		productRepo.deleteById(id);
	}

	final EntityManager entityManager;
	final CriteriaBuilder criteriaBuilder;

	public Page<Product> findByFilter(int page, int size , String sortBy, String sortType, Map<String, String> filters){
		List<Product> result;

		CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);

		Root<Product> orderRoot = criteriaQuery.from(Product.class);
		criteriaQuery.select(orderRoot);
			Predicate predicate = getPredicate(criteriaQuery, orderRoot, filters, false);
		criteriaQuery.where(predicate);
		if(Sort.Direction.ASC.name().equals(sortType)){
			criteriaQuery.orderBy(criteriaBuilder.asc(orderRoot.get(sortBy)));
		}else {
			criteriaQuery.orderBy(criteriaBuilder.desc(orderRoot.get(sortBy)));
		}

		TypedQuery<Product> typedQuery = entityManager.createQuery(criteriaQuery);
		typedQuery.setFirstResult(page * size);
		typedQuery.setMaxResults(size);
		Pageable pageable = getPageable(page, size, sortBy, sortType);
		long orderCount = getOrderCount(predicate);
		return new PageImpl<>(typedQuery.getResultList(), pageable, orderCount);
	}

	private long getOrderCount(Predicate predicate) {
		CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
		Root<Product> countRoot = countQuery.from(Product.class);
		countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
		return entityManager.createQuery(countQuery).getSingleResult();
	}

	private Pageable getPageable(int page, int size, String sortBy, String sortType) {
		Sort sort = Sort.by(Sort.Direction.fromString(sortType), sortBy);
		return PageRequest.of(page, size, sort);
	}

	private Predicate getPredicate(CriteriaQuery<Product> criteriaQuery, Root<Product> orderRoot, Map<String, String> filters, Boolean includeDeleted) {
		List<Predicate> predicateList = new ArrayList<>();
		if(!includeDeleted){
			predicateList.add(criteriaBuilder.equal(orderRoot.get(Product.DELETED), false));
		}
		if(filters.containsKey(Product.BRAND)){
			predicateList.add(criteriaBuilder.equal(orderRoot.get(Product.BRAND), filters.get(Product.BRAND)));
		}
		if(filters.containsKey(Product.SIZE)){
			predicateList.add(criteriaBuilder.equal(orderRoot.get(Product.SIZE), filters.get(Product.SIZE)));
		}
		if(filters.containsKey(Product.COLOR)){
			predicateList.add(criteriaBuilder.equal(orderRoot.get(Product.COLOR), filters.get(Product.COLOR)));
		}

		return criteriaBuilder.and(predicateList.stream().toArray(Predicate[] :: new));
	}

	public List<Product> getProductByColor(String color){
		List<Product> result;
		result = productRepo.findByColor(color);
		return result;
	}
	
	public List<Product> getProductBySize(int size){
		List<Product> result;
		result = productRepo.findBySize(size);
		return result;
	}
	
	public List<Product> getProductBySku(String sku){
		List<Product> result;
		result = productRepo.findBySku(sku);
		return result;
	}
	
	public List<Product> getProductByPrice(Double price){
		List<Product> result;
		result = productRepo.findByPrice(price);
		return result;
	}
	
	public long getProductQuantity(String sellerName, int productId) {
		List<Seller> seller = sellerRepo.findBySellerNameAndProductId(sellerName, productId);
		long count = 0;
		count = seller.stream().map(s->s.getQuantity()).reduce(0,Integer::sum);
		return count;
	}
	
	public long getInventory(int productId) {
		long count = 0;
		
		List<Seller> seller = sellerRepo.findByProductId(productId);
		count = seller.stream().map(s->s.getQuantity()).reduce(0, Integer::sum);
		return count;
	}
}
