package com.metropolitan.SE495.services.impl;

import com.metropolitan.SE495.entity.OrderItem;
import com.metropolitan.SE495.repository.OrderItemRepository;
import com.metropolitan.SE495.services.OrderItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class OrderItemServiceImpl implements OrderItemService {

    final OrderItemRepository repo;

    @Override
    public List<OrderItem> getAll() {
        return repo.findAll();
    }

    @Override
    public Optional<?> getById(Long id) {
        return repo.findById(id);
    }

    @Override
    public OrderItem add(OrderItem address) {
        return repo.save(address);
    }

    @Override
    public OrderItem update(OrderItem address) {
        return repo.save(address);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
