package com.azlir.restaurant.services.framework;

import com.azlir.restaurant.entities.database.StoreAccount;
import com.azlir.restaurant.entities.database.StoreAdmin;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface StoreAccountService extends UserDetailsService {
  StoreAccount findByEmail(String email);

  StoreAccount findByToken(String token);

  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

  void updatePassword(StoreAccount user);

  StoreAccount saveAdmin(StoreAccount user, StoreAdmin storeAdmin);
}
