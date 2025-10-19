package com.metropolitan.SE495.controllers;

import com.metropolitan.SE495.entity.Subscription;
import com.metropolitan.SE495.services.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subscription")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @GetMapping
    public ResponseEntity<List<Subscription>> getAllUsers() {
        return ResponseEntity.ok(subscriptionService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<?>> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(subscriptionService.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Subscription subscription) {
        return ResponseEntity.ok(subscriptionService.add(subscription));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Subscription subscription) {
        return ResponseEntity.ok(subscriptionService.update(subscription));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<?> subscription = subscriptionService.getById(id);
        if (subscription.isPresent()) {
            subscriptionService.delete(id);
            return ResponseEntity.ok(subscription.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Not found"));
    }
}
