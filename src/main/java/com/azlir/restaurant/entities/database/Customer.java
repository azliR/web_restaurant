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
    name = "customers",
    indexes = {@Index(name = "uk_customers_on_phone", columnList = "phone", unique = true)})
public class Customer implements Serializable {
  private static final long serialVersionUID = -7982102578834851837L;

  @Id
  @Column(name = "id", nullable = false)
  private UUID id;

  @Column(name = "full_name", nullable = false, length = 64)
  private String fullName;

  @Column(name = "phone", nullable = false, length = 16)
  private String phone;

  @Column(name = "language_code", nullable = false, length = 2)
  private String languageCode;

  @Column(name = "created_at", nullable = false)
  private Instant createdAt;

  @OneToMany(mappedBy = "customer")
  private Set<Order> orders = new LinkedHashSet<>();

  @ManyToMany
  @JoinTable(
      name = "coupon_customers",
      joinColumns = @JoinColumn(name = "customer_id"),
      inverseJoinColumns = @JoinColumn(name = "coupon_id"))
  private Set<Coupon> coupons = new LinkedHashSet<>();
}
