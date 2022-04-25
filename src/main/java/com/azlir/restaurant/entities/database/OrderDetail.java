package com.azlir.restaurant.entities.database;

import lombok.*;

import javax.persistence.*;
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
@Table(name = "order_details")
public class OrderDetail implements Serializable {
  private static final long serialVersionUID = -4017870032091296007L;

  @Id
  @Column(name = "id", nullable = false)
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "order_id", nullable = false)
  private Order order;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "item_id", nullable = false)
  private Item item;

  @Column(name = "item_name", nullable = false, length = 64)
  private String itemName;

  @Column(name = "quantity", nullable = false)
  private Boolean quantity = false;

  @Column(name = "price", nullable = false, precision = 11, scale = 2)
  private BigDecimal price;

  @Column(name = "netto", nullable = false, precision = 11, scale = 2)
  private BigDecimal netto;

  @Lob
  @Column(name = "picture")
  private String picture;

  @Column(name = "item_detail")
  private String itemDetail;

  @OneToMany(mappedBy = "orderDetail")
  private Set<OrderDetailAddon> orderDetailAddons = new LinkedHashSet<>();
}
