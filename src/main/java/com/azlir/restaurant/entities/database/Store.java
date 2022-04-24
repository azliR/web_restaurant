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
    name = "stores",
    indexes = {})
public class Store implements Serializable {
  private static final long serialVersionUID = -4792889357825217669L;

  @Id
  @Column(name = "id", nullable = false)
  private UUID id;

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "store_admin_id", nullable = false)
  private StoreAdmin storeAdmin;
}
