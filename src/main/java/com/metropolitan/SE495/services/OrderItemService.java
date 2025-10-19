package com.metropolitan.SE495.services;

import com.metropolitan.SE495.entity.OrderItem;

import java.util.List;
import java.util.Optional;

public interface OrderItemService {
    List<OrderItem> getAll();

    Optional<?> getById(Long id);

    OrderItem add(OrderItem address);

    OrderItem update(OrderItem address);

    void delete(Long id);
}
