package com.example.serachrestapi.service;

import com.example.serachrestapi.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> searchByNameOrDesc(String param);
}
