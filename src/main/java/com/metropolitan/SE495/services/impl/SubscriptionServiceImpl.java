package com.metropolitan.SE495.services.impl;

import com.metropolitan.SE495.entity.Subscription;
import com.metropolitan.SE495.repository.SubscriptionRepository;
import com.metropolitan.SE495.services.SubscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    final SubscriptionRepository subscriptionRepository;

    @Override
    public List<Subscription> getAll() {
        return subscriptionRepository.findAll();
    }

    @Override
    public Optional<?> getById(Long id) {
        return subscriptionRepository.findById(id);
    }

    @Override
    public Subscription add(Subscription address) {
        return subscriptionRepository.save(address);
    }

    @Override
    public Subscription update(Subscription address) {
        return subscriptionRepository.save(address);
    }

    @Override
    public void delete(Long id) {
        subscriptionRepository.deleteById(id);
    }
}
