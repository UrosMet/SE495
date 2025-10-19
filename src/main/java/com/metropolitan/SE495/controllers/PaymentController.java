package com.metropolitan.SE495.controllers;

import com.metropolitan.SE495.entity.Payment;
import com.metropolitan.SE495.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<Payment>> getAllUsers() {
        return ResponseEntity.ok(paymentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<?>> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Payment payment) {
        return ResponseEntity.ok(paymentService.add(payment));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Payment payment) {
        return ResponseEntity.ok(paymentService.update(payment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<?> payment = paymentService.getById(id);
        if (payment.isPresent()) {
            paymentService.delete(id);
            return ResponseEntity.ok(payment.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Not found"));
    }
}
