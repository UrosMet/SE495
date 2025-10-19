package com.metropolitan.SE495.services.impl;

import com.metropolitan.SE495.entity.Order;
import com.metropolitan.SE495.repository.OrderRepository;
import com.metropolitan.SE495.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    final OrderRepository orderRepository;
    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<?> getById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order add(Order address) {
        return orderRepository.save(address);
    }

    @Override
    public Order update(Order address) {
        return orderRepository.save(address);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
