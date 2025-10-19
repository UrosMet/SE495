package com.metropolitan.SE495.controllers;

import com.metropolitan.SE495.entity.Address;
import com.metropolitan.SE495.services.AddressService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {

    final AddressService addressService;

    @GetMapping
    public ResponseEntity<List<Address>> getAll() {
        return ResponseEntity.ok(addressService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        if (addressService.getById(id).isPresent()) {
            return ResponseEntity.ok(addressService.getById(id).get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Not found"));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Address address) {
        return ResponseEntity.ok(addressService.add(address));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Address address) {
        return ResponseEntity.ok(addressService.update(address));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<?> address = addressService.getById(id);
        if (address.isPresent()) {
            addressService.delete(id);
            return ResponseEntity.ok(address.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Not found"));
    }

}
