package com.likhith.fashion.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.likhith.fashion.document.Shirt;
import com.likhith.fashion.dto.Product;
import com.likhith.fashion.exception.ValidationException;
import com.likhith.fashion.mapper.FashionMapper;
import com.likhith.fashion.repository.ShirtRepository;

@Component
public class FashionServiceImpl implements FashionService {

	@Autowired
	ShirtRepository shirtRepository;

	@Autowired
	FashionMapper fashionMapper;

	@Override
	public List<Product> getAllProducts(String subCategoryName, boolean availability, double minPrice,
			double maxPrice) {

		List<Product> productList = new ArrayList<>();

		switch (subCategoryName.toLowerCase()) {
		case "shirt":
			List<Shirt> shirtList = shirtRepository.findByQuery(availability ? 0 : -1, minPrice, maxPrice);

			if (CollectionUtils.isEmpty(shirtList)) {
				throw new ValidationException(HttpStatus.NOT_FOUND.value(), "No shirts found");
			}

			productList = fashionMapper.mapToProductListFromShirtList(shirtList);
			break;
		default:
			throw new ValidationException(HttpStatus.NOT_IMPLEMENTED.value(),
					"Unsupported subCategory: " + subCategoryName);
		}
		return productList;
	}

	@Override
	public String addProduct(String subCategoryName, Product product) {

		String message = null;

		switch (subCategoryName.toLowerCase()) {
		case "shirt":

			Shirt shirt = fashionMapper.mapToShirtFromProduct(product);

			shirtRepository.findByNameAndBrandAndAttributes(shirt.getName(), shirt.getBrand(), shirt.getAttributes())
					.ifPresent(t -> {
						throw new ValidationException(HttpStatus.CONFLICT.value(), "Product already available");
					});

			shirtRepository.save(shirt);

			message = "Product added successfully";
			break;
		default:
			throw new ValidationException(HttpStatus.NOT_IMPLEMENTED.value(),
					"Unsupported subCategory: " + subCategoryName);
		}

		return message;
	}

	@Override
	public String updateProduct(String subCategoryName, Product product) {

		String message = null;

		switch (subCategoryName.toLowerCase()) {
		case "shirt":

			Shirt shirt = fashionMapper.mapToShirtFromProduct(product);

			Shirt shirtFromDB = shirtRepository
					.findByNameAndBrandAndAttributes(shirt.getName(), shirt.getBrand(), shirt.getAttributes())
					.orElseThrow(() -> {
						throw new ValidationException(HttpStatus.CONFLICT.value(), "Product not available");
					});

			shirt.setId(shirtFromDB.getId());

			shirtRepository.save(shirt);

			message = "Product updated successfully";
			break;
		default:
			throw new ValidationException(HttpStatus.NOT_IMPLEMENTED.value(),
					"Unsupported subCategory: " + subCategoryName);
		}

		return message;
	}

	@Override
	public String deleteProduct(String subCategoryName, Product product) {

		String message = null;

		switch (subCategoryName.toLowerCase()) {
		case "shirt":

			Shirt shirt = fashionMapper.mapToShirtFromProduct(product);

			Shirt shirtFromDB = shirtRepository
					.findByNameAndBrandAndAttributes(shirt.getName(), shirt.getBrand(), shirt.getAttributes())
					.orElseThrow(() -> {
						throw new ValidationException(HttpStatus.CONFLICT.value(), "Product not available");
					});

			shirtRepository.delete(shirtFromDB);

			message = "Product deleted successfully";
			break;
		default:
			throw new ValidationException(HttpStatus.NOT_IMPLEMENTED.value(),
					"Unsupported subCategory: " + subCategoryName);
		}

		return message;
	}

}