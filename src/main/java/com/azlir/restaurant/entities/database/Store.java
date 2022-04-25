package com.azlir.restaurant.entities.database;

import com.azlir.restaurant.entities.enums.PickupType;
import lombok.*;

import javax.persistence.*;
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
@Table(
    name = "stores",
    indexes = {
      @Index(name = "uk_stores_on_store_admin_id", columnList = "store_admin_id", unique = true),
      @Index(name = "uk_stores_on_phone", columnList = "phone", unique = true)
    })
public class Store implements Serializable {
  @Serial private static final long serialVersionUID = 8152217217842439488L;

  @Id
  @GeneratedValue
  @Column(name = "id", nullable = false)
  private UUID id;

  @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
  @JoinColumn(name = "store_admin_id", nullable = false)
  private StoreAdmin storeAdmin;

  @Column(name = "name", nullable = false, length = 64)
  private String name;

  @Column(name = "description")
  private String description;

  @Lob
  @Column(name = "image")
  private String image;

  @Lob
  @Column(name = "banner")
  private String banner;

  @Column(name = "phone", nullable = false, length = 16)
  private String phone;

  @Enumerated(EnumType.STRING)
  @Column(name = "pickup_type", columnDefinition = "pickup_type_enum not null")
  private PickupType pickupType;

  @Column(name = "street_address", nullable = false)
  private String streetAddress;

  @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.MERGE)
  @JoinColumn(name = "postcode_id", nullable = false)
  private Postcode postcode;

  @Column(name = "latitude", nullable = false)
  private Double latitude;

  @Column(name = "longitude", nullable = false)
  private Double longitude;

  @Column(name = "rating", precision = 2, scale = 1)
  private BigDecimal rating;

  @Column(name = "is_active", nullable = false)
  private Boolean isActive = false;

  @OneToMany(mappedBy = "store")
  private Set<StoreStaff> storeStaffs = new LinkedHashSet<>();

  @OneToMany(mappedBy = "store")
  private Set<Item> items = new LinkedHashSet<>();

  @OneToMany(mappedBy = "store")
  private Set<ReservationTable> reservationTables = new LinkedHashSet<>();

  @OneToMany(mappedBy = "store")
  private Set<Order> orders = new LinkedHashSet<>();

  @ManyToMany
  @JoinTable(
      name = "coupon_stores",
      joinColumns = @JoinColumn(name = "store_id"),
      inverseJoinColumns = @JoinColumn(name = "coupon_id"))
  private Set<Coupon> coupons = new LinkedHashSet<>();
}
