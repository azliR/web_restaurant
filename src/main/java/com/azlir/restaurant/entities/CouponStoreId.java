package com.azlir.restaurant.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Embeddable
public class CouponStoreId implements Serializable {
  @Serial private static final long serialVersionUID = -6958975203353062979L;

  @Column(name = "coupon_id", nullable = false)
  private UUID couponId;

  @Column(name = "store_id", nullable = false)
  private UUID storeId;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    CouponStoreId entity = (CouponStoreId) o;
    return Objects.equals(this.couponId, entity.couponId)
        && Objects.equals(this.storeId, entity.storeId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(couponId, storeId);
  }
}
