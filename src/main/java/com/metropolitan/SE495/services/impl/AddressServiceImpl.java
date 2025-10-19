package com.metropolitan.SE495.services.impl;

import com.metropolitan.SE495.entity.Address;
import com.metropolitan.SE495.repository.AddressRepository;
import com.metropolitan.SE495.services.AddressService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {

    final AddressRepository addressRepository;

    @Override
    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    @Override
    public Optional<?> getById(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    public Address add(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address update(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public void delete(Long id) {
        addressRepository.deleteById(id);
    }
}
