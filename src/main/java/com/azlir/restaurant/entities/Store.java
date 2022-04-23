package com.azlir.restaurant.entities;

import com.azlir.restaurant.core.PostgreSQLEnumType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "stores")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
public class Store implements Serializable {
  @Serial private static final long serialVersionUID = 7124230623713519709L;

  @Id
  @Column(name = "id", nullable = false, insertable = false, updatable = false)
  @Type(type = "org.hibernate.type.PostgresUUIDType")
  private UUID id;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @NotBlank
  @Size(max = 64)
  @Column(name = "name", nullable = false, length = 64)
  private String name;

  @Column(name = "description")
  private String description;

  @URL
  @Lob
  @Column(name = "image")
  private String image;

  @URL
  @Lob
  @Column(name = "banner")
  private String banner;

  @NotBlank
  @Size(min = 10, max = 16)
  @Column(name = "phone", nullable = false, length = 16)
  private String phone;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Type(type = "pgsql_enum")
  @Column(name = "pickup_type", nullable = false)
  private PickupType pickupType;

  @NotBlank
  @Size(max = 255)
  @Column(name = "street_address", nullable = false)
  private String streetAddress;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "postcode_id")
  private Postcode postcode;

  @NotNull
  @Column(name = "latitude", nullable = false)
  private Double latitude;

  @NotNull
  @Column(name = "longitude", nullable = false)
  private Double longitude;

  @Min(0)
  @Max(5)
  @Column(name = "rating", precision = 2, scale = 1)
  private BigDecimal rating;

  @NotNull
  @Column(name = "is_active", nullable = false)
  private Boolean isActive = false;

  @OneToMany(mappedBy = "store")
  private Set<Item> items = new LinkedHashSet<>();

  @OneToMany(mappedBy = "store")
  private Set<ReservationTable> tables = new LinkedHashSet<>();

  @OneToMany(mappedBy = "store")
  private Set<Order> orders = new LinkedHashSet<>();

  @ManyToMany
  @JoinTable(
      name = "coupon_stores",
      joinColumns = @JoinColumn(name = "store_id"),
      inverseJoinColumns = @JoinColumn(name = "coupon_id"))
  private Set<Coupon> coupons = new LinkedHashSet<>();

  enum PickupType {
    pickup,
    dine_in
  }
}
