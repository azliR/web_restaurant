package com.azlir.restaurant.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "store_accounts")
public class StoreAccount implements Serializable {
  @Serial private static final long serialVersionUID = 4856363504215226329L;

  @Id
  @Column(name = "id", nullable = false, insertable = false, updatable = false)
  private UUID id;

  @Column(name = "store_id", nullable = false)
  private UUID storeId;

  @Column(name = "full_name", nullable = false, length = 64)
  private String fullName;

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

  @Column(name = "created_at", nullable = false)
  private Instant createdAt;
}
