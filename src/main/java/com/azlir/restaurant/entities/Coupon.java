package com.azlir.restaurant.entities;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serial;
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
@Table(name = "coupons")
public class Coupon implements Serializable {
  @Serial private static final long serialVersionUID = 1642407666422649670L;

  @Id
  @Column(name = "id", nullable = false, insertable = false, updatable = false)
  @Type(type = "org.hibernate.type.PostgresUUIDType")
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "inserted_by", nullable = false)
  private User insertedBy;

  @Size(max = 16)
  @Column(name = "coupon_code", nullable = false, length = 16)
  private String couponCode;

  @NotBlank
  @Size(max = 64)
  @Column(name = "name", nullable = false, length = 64)
  private String name;

  @Column(name = "description")
  private String description;

  @Future
  @Column(name = "expiry_date", nullable = false)
  private Instant expiryDate;

  @Lob
  @Column(name = "discount_type", nullable = false)
  private String discountType;

  @Column(name = "discount", nullable = false, precision = 11, scale = 2)
  private BigDecimal discount;

  @Column(name = "min_total", nullable = false, precision = 11, scale = 2)
  private BigDecimal minTotal;

  @Column(name = "max_discount", nullable = false, precision = 11, scale = 2)
  private BigDecimal maxDiscount;

  @Column(name = "max_number_use_total")
  private Integer maxNumberUseTotal;

  @Column(name = "max_number_use_user", nullable = false)
  private Integer maxNumberUseUser;

  @Column(
      name = "created_at",
      nullable = false,
      insertable = false,
      updatable = false,
      columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private Instant createdAt;

  @Column(name = "all_store", nullable = false)
  private Boolean allStore = false;

  @Column(name = "all_user", nullable = false)
  private Boolean allUser = false;

  @Column(name = "is_valid", nullable = false)
  private Boolean isValid = false;

  @ManyToMany(mappedBy = "coupons")
  private Set<User> users = new LinkedHashSet<>();

  @OneToMany(mappedBy = "coupon")
  private Set<Order> orders = new LinkedHashSet<>();

  @ManyToMany(mappedBy = "coupons")
  private Set<Store> stores = new LinkedHashSet<>();
}
