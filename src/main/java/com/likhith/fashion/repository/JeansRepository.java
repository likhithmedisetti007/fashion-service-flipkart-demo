package com.likhith.fashion.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.likhith.fashion.document.Jeans;
import com.likhith.fashion.dto.Attribute;

public interface JeansRepository extends MongoRepository<Jeans, String> {

	@Query("{$and:[{'inventory.available': {$gt: ?0}}, {'price.amount': {$gte: ?1, $lte: ?2}}]}")
	List<Jeans> findByQuery(int availableCount, double minPrice, double maxPrice);

	Optional<Jeans> findByNameAndBrandAndAttributes(String name, String brand, List<Attribute> attributeList);

}