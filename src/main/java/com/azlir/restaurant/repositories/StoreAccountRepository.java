package com.azlir.restaurant.repositories;

import com.azlir.restaurant.entities.database.StoreAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StoreAccountRepository extends JpaRepository<StoreAccount, UUID> {}
