package com.azlir.restaurant.entities.database;

import com.azlir.restaurant.entities.enums.OrderStatus;
import com.azlir.restaurant.entities.enums.OrderType;
import com.azlir.restaurant.entities.enums.PickupType;
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
@Table(name = "orders")
public class Order implements Serializable {
  private static final long serialVersionUID = 1797456071835605925L;

  @Id
  @Column(name = "id", nullable = false)
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "customer_id", nullable = false)
  private Customer customer;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "store_id", nullable = false)
  private Store store;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "store_account_id")
  private StoreAccount storeAccount;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "table_id")
  private ReservationTable table;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "coupon_id")
  private Coupon coupon;

  @Column(name = "buyer", nullable = false, length = 64)
  private String buyer;

  @Lob
  @Column(name = "store_image")
  private String storeImage;

  @Lob
  @Column(name = "store_banner")
  private String storeBanner;

  @Column(name = "created_at", nullable = false)
  private Instant createdAt;

  @Column(name = "coupon_code", length = 16)
  private String couponCode;

  @Column(name = "coupon_name", length = 64)
  private String couponName;

  @Column(name = "discount", precision = 11, scale = 2)
  private BigDecimal discount;

  @Column(name = "discount_nominal", nullable = false, precision = 11, scale = 2)
  private BigDecimal discountNominal;

  @Column(name = "netto", nullable = false, precision = 11, scale = 2)
  private BigDecimal netto;

  @Column(name = "brutto", nullable = false, precision = 11, scale = 2)
  private BigDecimal brutto;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", columnDefinition = "order_status_enum not null")
  private OrderStatus status;

  @Enumerated(EnumType.STRING)
  @Column(name = "order_type", columnDefinition = "order_type_enum not null")
  private OrderType orderType;

  @Column(name = "scheduled_at")
  private Instant scheduledAt;

  @Enumerated(EnumType.STRING)
  @Column(name = "pickup_type", columnDefinition = "pickup_type_enum not null")
  private PickupType pickupType;

  @Column(name = "rating", precision = 2, scale = 1)
  private BigDecimal rating;

  @Column(name = "comment")
  private String comment;

  @OneToMany(mappedBy = "order")
  private Set<OrderDetail> orderDetails = new LinkedHashSet<>();
}
