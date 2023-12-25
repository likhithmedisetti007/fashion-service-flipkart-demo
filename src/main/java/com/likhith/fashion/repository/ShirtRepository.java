package com.likhith.fashion.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.likhith.fashion.document.Shirt;
import com.likhith.fashion.dto.Attribute;

public interface ShirtRepository extends MongoRepository<Shirt, String> {

	@Query("{$and:[{'inventory.available': {$gt: ?0}}, {'price.amount': {$gte: ?1, $lte: ?2}}]}")
	List<Shirt> findByQuery(int availableCount, double minPrice, double maxPrice);

	Optional<Shirt> findByNameAndBrandAndAttributes(String name, String brand, List<Attribute> attributeList);

}