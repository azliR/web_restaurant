package com.azlir.restaurant.entities;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
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
@Table(name = "orders")
public class Order implements Serializable {
    @Serial private static final long serialVersionUID = 4942378204165514921L;

    @Id
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

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

    @Column(
            name = "created_at",
            nullable = false,
            insertable = false,
            updatable = false,
            columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP")
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

    @Lob
    @Column(name = "status", nullable = false)
    private String status;

    @Lob
    @Column(name = "order_type", nullable = false)
    private String orderType;

    @Column(name = "scheduled_at")
    private Instant scheduledAt;

    @Lob
    @Column(name = "pickup_type", nullable = false)
    private String pickupType;

    @Column(name = "rating", precision = 2, scale = 1)
    private BigDecimal rating;

    @Column(name = "comment")
    private String comment;

    @OneToMany(mappedBy = "order")
    private Set<OrderDetail> orderDetails = new LinkedHashSet<>();
}
