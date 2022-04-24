package com.azlir.restaurant.entities.database;

import com.azlir.restaurant.entities.enums.StoreRole;
import lombok.*;
import org.hibernate.annotations.GenerationTime;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "store_accounts")
public class StoreAccount implements Serializable, UserDetails {
  @Serial
  private static final long serialVersionUID = 927799691005697180L;

  @Id
  @GeneratedValue
  @Column(name = "id", nullable = false)
  private UUID id;

  @Column(name = "full_name", nullable = false, length = 64)
  private String fullName;

  @Enumerated(EnumType.STRING)
  @Column(name = "role", columnDefinition = "store_role_enum not null")
  private StoreRole role;

  @org.hibernate.annotations.Generated(value = GenerationTime.INSERT)
  @Column(name = "created_at", nullable = false)
  private Instant createdAt;

  @OneToOne(fetch = FetchType.LAZY, mappedBy = "storeAccount", cascade = CascadeType.ALL)
  @PrimaryKeyJoinColumn
  private StoreAdmin storeAdmin;

  @OneToOne(fetch = FetchType.LAZY, mappedBy = "storeAccounts", cascade = CascadeType.ALL)
  @PrimaryKeyJoinColumn
  private StoreStaff storeStaff;

  @OneToMany(mappedBy = "storeAccount")
  private Set<Order> orders = new LinkedHashSet<>();

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Set<GrantedAuthority> authorities = new HashSet<>();
    authorities.add(new SimpleGrantedAuthority(role.toString()));
    return authorities;
  }

  @Override
  public String getPassword() {
    return switch (role) {
      case admin -> storeAdmin.getPassword();
      case staff -> storeStaff.getPassword();
    };
  }

  @Override
  public String getUsername() {
    return switch (role) {
      case admin -> storeAdmin.getEmail();
      case staff -> storeStaff.getUsername();
    };
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
