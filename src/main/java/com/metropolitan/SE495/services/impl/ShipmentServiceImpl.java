package com.metropolitan.SE495.services.impl;

import com.metropolitan.SE495.entity.Shipment;
import com.metropolitan.SE495.repository.ShipmentRepository;
import com.metropolitan.SE495.services.ShipmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ShipmentServiceImpl implements ShipmentService {

    final ShipmentRepository shipmentRepository;

    @Override
    public List<Shipment> getAll() {
        return shipmentRepository.findAll();
    }

    @Override
    public Optional<?> getById(Long id) {
        return shipmentRepository.findById(id);
    }

    @Override
    public Shipment add(Shipment address) {
        return shipmentRepository.save(address);
    }

    @Override
    public Shipment update(Shipment address) {
        return shipmentRepository.save(address);
    }

    @Override
    public void delete(Long id) {
        shipmentRepository.deleteById(id);
    }
}
