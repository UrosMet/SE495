package com.metropolitan.SE495.services;

import com.metropolitan.SE495.entity.Address;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;


public interface AddressService {

    List<Address> getAll();

    Optional<?> getById(Long id);

    Address add(Address address);

    Address update(Address address);

    void delete(Long id);
}
