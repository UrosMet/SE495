package com.metropolitan.SE495.services.impl;

import com.metropolitan.SE495.entity.Payment;
import com.metropolitan.SE495.repository.PaymentRepository;
import com.metropolitan.SE495.services.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    final PaymentRepository paymentRepository;

    @Override
    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Optional<?> getById(Long id) {
        return paymentRepository.findById(id);
    }

    @Override
    public Payment add(Payment address) {
        return paymentRepository.save(address);
    }

    @Override
    public Payment update(Payment address) {
        return paymentRepository.save(address);
    }

    @Override
    public void delete(Long id) {
        paymentRepository.deleteById(id);
    }
}
