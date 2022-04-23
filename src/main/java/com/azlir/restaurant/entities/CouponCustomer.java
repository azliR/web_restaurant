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
@Table(name = "coupon_customers")
public class CouponCustomer implements Serializable {
  @Serial private static final long serialVersionUID = 3454820547449141787L;
  @EmbeddedId private CouponCustomerId id;

  @MapsId("customerId")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "customer_id", nullable = false)
  private Customer customer;
}
