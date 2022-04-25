package com.azlir.restaurant.entities.database;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
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
@Table(name = "item_addon_categories")
public class ItemAddonCategory implements Serializable {
  private static final long serialVersionUID = 9047358667881076903L;

  @Id
  @Column(name = "id", nullable = false)
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "item_id", nullable = false)
  private Item item;

  @Column(name = "name", nullable = false, length = 64)
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "is_multiple_choice", nullable = false)
  private Boolean isMultipleChoice = false;

  @OneToMany(mappedBy = "addonCategory")
  private Set<ItemAddon> itemAddons = new LinkedHashSet<>();
}
