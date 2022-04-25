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
@Table(name = "item_addons")
public class ItemAddon implements Serializable {
  private static final long serialVersionUID = -2230030667103685302L;

  @Id
  @Column(name = "id", nullable = false)
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "addon_category_id", nullable = false)
  private ItemAddonCategory addonCategory;

  @Column(name = "name", nullable = false, length = 64)
  private String name;

  @Column(name = "price", nullable = false, precision = 11, scale = 2)
  private BigDecimal price;

  @OneToMany(mappedBy = "addon")
  private Set<OrderDetailAddon> orderDetailAddons = new LinkedHashSet<>();
}
