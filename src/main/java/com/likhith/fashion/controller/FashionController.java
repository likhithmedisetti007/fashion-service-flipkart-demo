package com.likhith.fashion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public ResponseEntity<FashionResponse> getAllProducts(@PathVariable("subCategoryName") String subCategoryName) {

		List<Product> products = fashionService.getAllProducts(subCategoryName);

		if (!CollectionUtils.isEmpty(products)) {
			return ResponseEntity.ok().body(new FashionResponse(subCategoryName, products));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND.value())
					.body(new FashionResponse(new ErrorMessage(HttpStatus.NOT_FOUND.value(),
							"No products found for the subCategory: " + subCategoryName)));
		}
	}

}