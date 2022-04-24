package com.azlir.restaurant.repositories;

import com.azlir.restaurant.entities.database.StoreAccount;
import com.azlir.restaurant.entities.database.StoreAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreAdminRepository extends JpaRepository<StoreAdmin, StoreAccount> {
  Optional<StoreAdmin> findByEmail(String email);

  Optional<StoreAdmin> findByTokenResetPassword(String token);
}
