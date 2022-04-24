package com.azlir.restaurant.services.implementation;

import com.azlir.restaurant.entities.database.StoreAccount;
import com.azlir.restaurant.entities.database.StoreAdmin;
import com.azlir.restaurant.entities.enums.StoreRole;
import com.azlir.restaurant.repositories.StoreAccountRepository;
import com.azlir.restaurant.repositories.StoreAdminRepository;
import com.azlir.restaurant.services.framework.StoreAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StoreAccountServiceImpl implements StoreAccountService {
  private final StoreAccountRepository storeAccountRepository;
  private final StoreAdminRepository storeAdminRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  @Autowired
  public StoreAccountServiceImpl(
          StoreAccountRepository storeAccountRepository, StoreAdminRepository storeAdminRepository, BCryptPasswordEncoder passwordEncoder) {
    this.storeAccountRepository = storeAccountRepository;
    this.storeAdminRepository = storeAdminRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public StoreAccount findByEmail(String email) {
    final var storeAdmin = storeAdminRepository.findByEmail(email).orElse(null);
    if (storeAdmin!=null) {
      return storeAccountRepository.findById(storeAdmin.getId()).orElse(null);
    }
    return null;
  }

  @Override
  public StoreAccount findByToken(String token) {
    final var storeAdmin = storeAdminRepository.findByTokenResetPassword(token).orElse(null);
    if (storeAdmin != null) {
      return storeAccountRepository.findById(storeAdmin.getId()).orElse(null);
    }
    return null;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    final var storeAccount = findByEmail(email);
    if (storeAccount == null){
      throw new UsernameNotFoundException("Email not found!");
    }
    new AccountStatusUserDetailsChecker().check(storeAccount);
    return storeAccount;
  }

  @Override
  @Modifying
  public void updatePassword(StoreAccount storeAccount) {
    switch (storeAccount.getRole()) {
      case staff -> {
        final var staff = storeAccount.getStoreStaff();
        staff.setPassword(passwordEncoder.encode(storeAccount.getPassword()));
        storeAccount.setStoreStaff(storeAccount.getStoreStaff());
      } case admin -> {
        final var admin = storeAccount.getStoreAdmin();
        admin.setPassword(passwordEncoder.encode(storeAccount.getPassword()));
        storeAccount.setStoreAdmin(storeAccount.getStoreAdmin());
      }
    }
    storeAccountRepository.save(storeAccount);
  }

  @Override
  public StoreAccount saveAdmin(StoreAccount storeAccount, StoreAdmin storeAdmin) {
    storeAccount.setRole(StoreRole.admin);
    storeAccount.setStoreAdmin(storeAdmin);
    storeAdmin.setStoreAccount(storeAccount);
    storeAdmin.setPassword(passwordEncoder.encode(storeAdmin.getPassword()));
    return storeAccountRepository.save(storeAccount);
  }
}
