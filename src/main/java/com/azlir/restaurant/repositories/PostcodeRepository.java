package com.azlir.restaurant.repositories;

import com.azlir.restaurant.entities.database.Postcode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostcodeRepository extends JpaRepository<Postcode, String> {
}
