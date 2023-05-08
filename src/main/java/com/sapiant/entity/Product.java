package com.sapiant.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	public static final String DELETED = "isDeleted";
	public static final String BRAND = "brand";
	public static final String COLOR = "color";
	public static final String SIZE = "size";
	@Id
	@GeneratedValue
	private int productId;
	private String productName;
	private String color;
	private double price;
	private String brand;
	private int size;
	private String sku;
}
