package com.metropolitan.SE495.services;

import com.metropolitan.SE495.entity.Subscription;

import java.util.List;
import java.util.Optional;

public interface SubscriptionService {
    List<Subscription> getAll();

    Optional<?> getById(Long id);

    Subscription add(Subscription address);

    Subscription update(Subscription address);

    void delete(Long id);
}
