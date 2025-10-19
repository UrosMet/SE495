package com.metropolitan.SE495.services;

import com.metropolitan.SE495.entity.Shipment;

import java.util.List;
import java.util.Optional;

public interface ShipmentService {
    List<Shipment> getAll();

    Optional<?> getById(Long id);

    Shipment add(Shipment address);

    Shipment update(Shipment address);

    void delete(Long id);
}
