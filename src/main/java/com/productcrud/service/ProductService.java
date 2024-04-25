package com.productcrud.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.productcrud.dto.ProductAttributes;
import com.productcrud.dto.ProductRatingRequest;
import com.productcrud.dto.ProductRatings;
import com.productcrud.model.Product;
import com.productcrud.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	/**
	 * get all products
	 * 
	 * @return
	 */
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	/**
	 * get products using pegination
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Product> getProductUsingPagination(int pageNo, int pageSize) {

		PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by("name").ascending());
		Page<Product> pagingUser = productRepository.findAll(pageRequest);
		return pagingUser.getContent();
	}

	/**
	 * add product
	 * 
	 * @param product
	 */
	public void addProduct(Product product) {
		String uuid = UUID.randomUUID().toString();
		product.setId(uuid);
		productRepository.save(product);
		System.out.println("record added in db");
	}

	/**
	 * add product ratings
	 * 
	 * @param productRatingRequest
	 * @return
	 */
	public boolean addProductRating(ProductRatingRequest productRatingRequest) {
		Product product = findById(productRatingRequest.getProductId());
		if (product == null) {
			return false;
		}

		// new feedback
		ProductRatings productRatings = new ProductRatings();
		productRatings.setUserId(productRatingRequest.getUserId());
		productRatings.setRating(productRatingRequest.getRating());
		productRatings.setComment(productRatingRequest.getComment());

		List<ProductRatings> ratings = product.getRatings();
		ratings.add(productRatings);
		product.setRatings(ratings);

		productRepository.save(product);
		System.out.println("product ratting added");
		return true;
	}

	public boolean deleteProduct(String id) {
		// check product exists in db
		boolean isExists = productRepository.existsById(id);
		if (isExists == false) {
			return false;
		}
		productRepository.deleteById(id);
		System.out.println("product deleted");
		return true;
	}

	public ProductService updateProduct(Product product) {
		productRepository.save(product);
		return null;
	}

	public Product findById(String id) {
		Optional<Product> products = productRepository.findById(id);
		return products.get();
	}

	public List<Product> findByname(String name) {
		List<Product> products = productRepository.findByName(name);
		return products;
	}

	/**
	 * filter the product
	 * @param product
	 * @return
	 */
	public List<Product> filterProducts(Product product) {
		String name = product.getName();
		List<String> categories = product.getCategories();
		List<ProductAttributes> attributes = product.getAttributes();
		
		Query query = new Query();

		// Add criteria for name
		if (name != null && !name.isEmpty()) {
			query.addCriteria(Criteria.where("name").is(name));
		}

		// Add criteria for category
		if (categories != null && !categories.isEmpty()) {
			query.addCriteria(Criteria.where("categories").in(categories));
		}

		// Add criteria for attributes
		if (attributes != null && !attributes.isEmpty()) {
			for (ProductAttributes attribute : attributes) {
				if (attribute.getBrand() != null && !attribute.getBrand().isEmpty()) {
					query.addCriteria(Criteria.where("attributes." + "brand").is(attribute.getBrand()));
				}

				if (attribute.getColor() != null && !attribute.getColor().isEmpty()) {
					query.addCriteria(Criteria.where("attributes." + "color").is(attribute.getColor()));
				}

				if (attribute.getSize() != null && !attribute.getSize().isEmpty()) {
					query.addCriteria(Criteria.where("attributes." + "size").is(attribute.getSize()));
				}
			}
		}
		return mongoTemplate.find(query, Product.class);
	}
}