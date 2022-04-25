package com.azlir.restaurant.repositories;

import com.azlir.restaurant.entities.database.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {}
