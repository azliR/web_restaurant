package com.azlir.restaurant.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "coupon_stores")
public class CouponStore implements Serializable {
  @Serial private static final long serialVersionUID = -1893823606402099034L;
  @EmbeddedId private CouponStoreId id;

  @MapsId("storeId")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "store_id", nullable = false)
  private Store store;
}
