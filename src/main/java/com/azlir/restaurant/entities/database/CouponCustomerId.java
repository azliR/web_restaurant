package com.azlir.restaurant.entities.database;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
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
public class CouponCustomerId implements Serializable {
  private static final long serialVersionUID = 518791617650159255L;

  @Column(name = "coupon_id", nullable = false)
  private UUID couponId;

  @Column(name = "customer_id", nullable = false)
  private UUID customerId;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    CouponCustomerId entity = (CouponCustomerId) o;
    return Objects.equals(this.customerId, entity.customerId)
        && Objects.equals(this.couponId, entity.couponId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customerId, couponId);
  }
}
