package com.azlir.restaurant.services;

import com.azlir.restaurant.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    List<User> getAllUsers();

    Optional<User> findById(UUID id);

    User save(User std);

    void deleteById(UUID id);
}
