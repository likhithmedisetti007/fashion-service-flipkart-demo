package com.likhith.fashion.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.likhith.fashion.document.Shirt;

public interface ShirtRepository extends MongoRepository<Shirt, String> {

}