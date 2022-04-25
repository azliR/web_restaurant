package com.azlir.restaurant.repositories;

import com.azlir.restaurant.entities.database.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemCategoryRepository extends JpaRepository<ItemCategory, UUID> {
}
