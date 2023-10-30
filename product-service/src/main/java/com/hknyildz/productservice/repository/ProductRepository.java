package com.hknyildz.productservice.repository;

import com.hknyildz.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,String> {



}
