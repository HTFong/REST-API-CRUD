package com.example.serachrestapi.service.impl;

import com.example.serachrestapi.entity.Product;
import com.example.serachrestapi.repository.ProductRepository;
import com.example.serachrestapi.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepo;

    public ProductServiceImpl(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public List<Product> searchByNameOrDesc(String param) {
        return productRepo.findByNameAndDescSQL(param);
    }
}
