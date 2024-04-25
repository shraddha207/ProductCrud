package com.productcrud.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.productcrud.dto.ProductAttributes;
import com.productcrud.dto.ProductAvailability;
import com.productcrud.dto.ProductRatings;


@Document(collection = "product")
public class Product {
	private String id;
	private String name;
	private String description;
	private Double price;
	private List<String> categories;
	private List<ProductAttributes> attributes;
	private ProductAvailability availability;
	private List<ProductRatings> ratings;

	public String getId() {
		return id;
	}

	public void setId(String i) {
		this.id = i;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public List<ProductAttributes> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<ProductAttributes> attributes) {
		this.attributes = attributes;
	}

	public ProductAvailability getAvailability() {
		return availability;
	}

	public void setAvailability(ProductAvailability availability) {
		this.availability = availability;
	}

	public List<ProductRatings> getRatings() {
		return ratings;
	}

	public void setRatings(List<ProductRatings> ratings) {
		this.ratings = ratings;
	}
}
