package com.azlir.restaurant.entities.database;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(
    name = "store_staffs",
    indexes = {
      @Index(
          name = "uk_store_staffs_on_store_id_username",
          columnList = "store_id, username",
          unique = true)
    })
public class StoreStaff implements Serializable {
  private static final long serialVersionUID = 9165130305418189312L;

  @Id
  @Column(name = "store_account_id", nullable = false)
  private UUID id;

  @MapsId
  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "store_account_id", nullable = false)
  private StoreAccount storeAccounts;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "store_id", nullable = false)
  private Store store;

  @Column(name = "username", nullable = false, length = 36)
  private String username;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "is_locked", nullable = false)
  private Boolean isLocked = false;
}
