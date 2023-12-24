package com.likhith.fashion.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.likhith.fashion.dto.Product;

@Service
public interface FashionService {

	List<Product> getAllProducts(String subCategoryName);

}