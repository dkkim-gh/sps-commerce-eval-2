package com.sps.eval.service;

import com.sps.eval.model.Product;
import com.sps.eval.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Optional<Product> findByProductId(String id) {
        return productRepository.findById(id);
    }
}
