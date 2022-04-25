package com.azlir.restaurant.entities.database;

import com.azlir.restaurant.entities.enums.DiscountType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
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
    name = "coupons",
    indexes = {
      @Index(
          name = "uk_coupons_on_coupon_code_is_valid",
          columnList = "coupon_code, is_valid",
          unique = true)
    })
public class Coupon implements Serializable {
  private static final long serialVersionUID = -8720610662952747898L;

  @Id
  @Column(name = "id", nullable = false)
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "inserted_by", nullable = false)
  private StoreAdmin insertedBy;

  @Column(name = "coupon_code", nullable = false, length = 16)
  private String couponCode;

  @Column(name = "name", nullable = false, length = 64)
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "expiry_date", nullable = false)
  private Instant expiryDate;

  @Enumerated(EnumType.STRING)
  @Column(name = "discount_type", columnDefinition = "discount_type_enum not null")
  private DiscountType discountType;

  @Column(name = "discount", nullable = false, precision = 11, scale = 2)
  private BigDecimal discount;

  @Column(name = "min_total", nullable = false, precision = 11, scale = 2)
  private BigDecimal minTotal;

  @Column(name = "max_discount", nullable = false, precision = 11, scale = 2)
  private BigDecimal maxDiscount;

  @Column(name = "max_number_use_total")
  private Integer maxNumberUseTotal;

  @Column(name = "max_number_use_customer", nullable = false)
  private Integer maxNumberUseCustomer;

  @Column(name = "created_at", nullable = false)
  private Instant createdAt;

  @Column(name = "all_store", nullable = false)
  private Boolean allStore = false;

  @Column(name = "all_customer", nullable = false)
  private Boolean allCustomer = false;

  @Column(name = "is_valid", nullable = false)
  private Boolean isValid = false;

  @OneToMany(mappedBy = "coupon")
  private Set<Order> orders = new LinkedHashSet<>();

  @ManyToMany
  @JoinTable(
      name = "coupon_stores",
      joinColumns = @JoinColumn(name = "coupon_id"),
      inverseJoinColumns = @JoinColumn(name = "store_id"))
  private Set<Store> stores = new LinkedHashSet<>();

  @ManyToMany
  @JoinTable(
      name = "coupon_customers",
      joinColumns = @JoinColumn(name = "coupon_id"),
      inverseJoinColumns = @JoinColumn(name = "customer_id"))
  private Set<Customer> customers = new LinkedHashSet<>();
}
