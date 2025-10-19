package com.metropolitan.SE495.services;

import com.metropolitan.SE495.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAll();

    Optional<?> getById(Long id);

    Product add(Product address);

    Product update(Product address);

    void delete(Long id);
}
