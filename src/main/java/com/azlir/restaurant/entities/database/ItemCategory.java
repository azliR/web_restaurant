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
@Table(name = "item_categories")
public class ItemCategory implements Serializable {
  private static final long serialVersionUID = 2758376052488662028L;

  @Id
  @GeneratedValue
  @Column(name = "id", nullable = false)
  private UUID id;

  @Column(name = "name", nullable = false, length = 64)
  private String categoryName;

  @OneToMany(mappedBy = "category")
  private Set<ItemCategoryL10n> itemCategoryL10ns = new LinkedHashSet<>();

  @OneToMany(mappedBy = "category")
  private Set<Item> items = new LinkedHashSet<>();
}
