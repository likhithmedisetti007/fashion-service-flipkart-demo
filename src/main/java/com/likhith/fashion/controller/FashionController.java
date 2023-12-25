package com.likhith.fashion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.likhith.fashion.dto.FashionResponse;
import com.likhith.fashion.dto.Product;
import com.likhith.fashion.exception.ErrorMessage;
import com.likhith.fashion.service.FashionService;

@RestController
@RequestMapping("/fashion")
public class FashionController {

	@Autowired
	FashionService fashionService;

	@GetMapping("/{subCategoryName}/getAllProducts")
	public ResponseEntity<FashionResponse> getAllProducts(@PathVariable("subCategoryName") String subCategoryName,
			@RequestParam(name = "availability") boolean availability, @RequestParam(name = "minPrice") double minPrice,
			@RequestParam(name = "maxPrice") double maxPrice) {

		List<Product> products = fashionService.getAllProducts(subCategoryName, availability, minPrice, maxPrice);

		if (!CollectionUtils.isEmpty(products)) {
			return ResponseEntity.ok().body(new FashionResponse(subCategoryName, products));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND.value())
					.body(new FashionResponse(new ErrorMessage(HttpStatus.NOT_FOUND.value(),
							"No products found for the subCategory: " + subCategoryName)));
		}
	}

	@PostMapping("/{subCategoryName}/addProduct")
	public ResponseEntity<String> addProduct(@PathVariable("subCategoryName") String subCategoryName,
			@RequestBody Product product) {

		String message = fashionService.addProduct(subCategoryName, product);
		return ResponseEntity.ok().body(message);
	}

	@PutMapping("/{subCategoryName}/updateProduct")
	public ResponseEntity<String> updateProduct(@PathVariable("subCategoryName") String subCategoryName,
			@RequestBody Product product) {

		String message = fashionService.updateProduct(subCategoryName, product);
		return ResponseEntity.ok().body(message);
	}

	@DeleteMapping("/{subCategoryName}/deleteProduct")
	public ResponseEntity<String> deleteProduct(@PathVariable("subCategoryName") String subCategoryName,
			@RequestBody Product product) {

		String message = fashionService.deleteProduct(subCategoryName, product);
		return ResponseEntity.ok().body(message);
	}

}