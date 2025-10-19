package com.metropolitan.SE495.controllers;

import com.metropolitan.SE495.entity.Shipment;
import com.metropolitan.SE495.services.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shipment")
public class ShipmentController {
    private final ShipmentService shipmentService;

    @GetMapping
    public ResponseEntity<List<Shipment>> getAllUsers() {
        return ResponseEntity.ok(shipmentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<?>> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(shipmentService.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Shipment shipment) {
        return ResponseEntity.ok(shipmentService.add(shipment));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Shipment shipment) {
        return ResponseEntity.ok(shipmentService.update(shipment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<?> shipment = shipmentService.getById(id);
        if (shipment.isPresent()) {
            shipmentService.delete(id);
            return ResponseEntity.ok(shipment.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Not found"));
    }
}
