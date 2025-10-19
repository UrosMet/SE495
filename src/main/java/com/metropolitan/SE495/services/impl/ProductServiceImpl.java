package com.metropolitan.SE495.services.impl;

import com.metropolitan.SE495.entity.Product;
import com.metropolitan.SE495.repository.ProductRepository;
import com.metropolitan.SE495.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    final ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<?> getById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product add(Product address) {
        return productRepository.save(address);
    }

    @Override
    public Product update(Product address) {
        return productRepository.save(address);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
