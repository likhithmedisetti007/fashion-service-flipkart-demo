package com.likhith.fashion.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

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
	public List<Product> getAllProducts(String subCategoryName) {

		List<Product> subCategoryList = new ArrayList<>();

		switch (subCategoryName.toLowerCase()) {
		case "shirt":
			List<Shirt> shirtList = shirtRepository.findAll();
			subCategoryList = fashionMapper.mapToSubCategoryFromShirt(shirtList);
			break;
		default:
			throw new ValidationException(HttpStatus.NOT_IMPLEMENTED.value(),
					"Unsupported subCategory: " + subCategoryName);
		}
		return subCategoryList;
	}

}