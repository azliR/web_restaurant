package com.azlir.restaurant.entities.database;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(
    name = "store_admins",
    indexes = {@Index(name = "uk_store_admins_on_email", columnList = "email", unique = true)})
public class StoreAdmin implements Serializable {
  private static final long serialVersionUID = 2905396033211795518L;

  @Id
  @Column(name = "store_account_id", nullable = false)
  private UUID id;

  @MapsId
  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "store_account_id", nullable = false)
  private StoreAccount storeAccount;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "token_reset_password")
  private String tokenResetPassword;

  @Column(name = "token_expired_at")
  private Instant tokenExpiredAt;

  @Column(name = "last_updated_password")
  private Instant lastUpdatedPassword;

  @OneToMany(mappedBy = "insertedBy")
  private Set<Coupon> coupons = new LinkedHashSet<>();

  @OneToOne(fetch = FetchType.LAZY, mappedBy = "storeAdmin")
  private Store store;
}
