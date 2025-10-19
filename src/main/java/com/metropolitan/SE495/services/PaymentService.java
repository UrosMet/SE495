package com.metropolitan.SE495.services;

import com.metropolitan.SE495.entity.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    List<Payment> getAll();

    Optional<?> getById(Long id);

    Payment add(Payment address);

    Payment update(Payment address);

    void delete(Long id);
}
