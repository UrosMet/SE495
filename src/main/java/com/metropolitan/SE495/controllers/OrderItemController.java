package com.metropolitan.SE495.controllers;

import com.metropolitan.SE495.entity.OrderItem;
import com.metropolitan.SE495.services.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orderitem")
public class OrderItemController {
    private final OrderItemService orderItemService;

    @GetMapping
    public ResponseEntity<List<OrderItem>> getAllUsers() {
        return ResponseEntity.ok(orderItemService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<?>> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(orderItemService.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody OrderItem orderItem) {
        return ResponseEntity.ok(orderItemService.add(orderItem));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody OrderItem orderItem) {
        return ResponseEntity.ok(orderItemService.update(orderItem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<?> orderItem = orderItemService.getById(id);
        if (orderItem.isPresent()) {
            orderItemService.delete(id);
            return ResponseEntity.ok(orderItem.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Not found"));
    }
}
