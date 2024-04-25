package com.productcrud.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.productcrud.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product , String> {
	public void deleteById(String id);
	public List<Product> findByName(String name);
	public Optional<Product> findById(String id);
}