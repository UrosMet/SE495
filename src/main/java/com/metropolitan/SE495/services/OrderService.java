package com.metropolitan.SE495.services;

import com.metropolitan.SE495.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> getAll();

    Optional<?> getById(Long id);

    Order add(Order address);

    Order update(Order address);

    void delete(Long id);
}
