package com.productcrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.productcrud.dto.ProductRatingRequest;
import com.productcrud.dto.ProductResponse;
import com.productcrud.model.Product;
import com.productcrud.service.ProductService;

@RestController
@CrossOrigin(origins = "")
@RequestMapping("/api")
public class ProductController {
	@Autowired
	private ProductService productService;

	@PostMapping(path = "/add/product")
	public ProductResponse addProduct(@RequestBody Product product) {
		ProductResponse productResponse = new ProductResponse();
		try {
			productService.addProduct(product);
			productResponse.setStatus(true);
			productResponse.setMessage("product added successfully");
		} catch (Exception e) {
			e.printStackTrace();
			productResponse.setStatus(false);
			productResponse.setMessage("error while adding product");
		}
		return productResponse;
	}
	
	@PostMapping(path = "/filter/product")
	public List<Product> filterProduct(@RequestBody Product product) {
		ProductResponse productResponse = new ProductResponse();
		try {
			List<Product> products = productService.filterProducts(product);
			return products;
		} catch (Exception e) {
			e.printStackTrace();
			productResponse.setStatus(false);
			productResponse.setMessage("error while fetching product");
			return null;
		}
		//return productResponse;
	}

	@PostMapping(path = "/add/product/rating ")
	public ProductResponse addProduct(@RequestBody ProductRatingRequest productRatingRequest) {
		ProductResponse productResponse = new ProductResponse();
		try {
			boolean status = productService.addProductRating(productRatingRequest);
			if (status) {
				productResponse.setStatus(true);
				productResponse.setMessage("product rating added successfully");
			} else {
				productResponse.setMessage("product id not exists");
			}
		} catch (Exception e) {
			e.printStackTrace();
			productResponse.setStatus(false);
			productResponse.setMessage("error while adding rating product");
		}
		return productResponse;
	}

	@GetMapping("/get/product")
	public List<Product> getProducts() {
		return productService.getAllProducts();
	}
	
	@GetMapping("/pagination")
	public List<Product> getUsersByPagination(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize) {

		return productService.getProductUsingPagination(pageNo, pageSize);

	}

	@DeleteMapping("/delete/product")
	public ProductResponse deleteProduct(@RequestParam String id) {
		ProductResponse productResponse = new ProductResponse();
		try {
			boolean status = productService.deleteProduct(id);
			if (status) {
				productResponse.setMessage("product deleted successfully");
			} else {
				productResponse.setMessage("product id not exists");
			}
			productResponse.setStatus(true);
		} catch (Exception e) {
			e.printStackTrace();
			productResponse.setStatus(false);
			productResponse.setMessage("error while deleting product");
		}
		return productResponse;
	}

	@PutMapping("/update/product")
	public ProductResponse updateProduct(@RequestBody Product product) {
		ProductResponse productResponse = new ProductResponse();
		try {
			productService.updateProduct(product);
			productResponse.setStatus(true);
			productResponse.setMessage("product updated successfully");
		} catch (Exception e) {
			e.printStackTrace();
			productResponse.setStatus(false);
			productResponse.setMessage("error while updating product");
		}
		return productResponse;
	}
}