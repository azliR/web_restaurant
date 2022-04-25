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
@Table(name = "item_sub_categories")
public class ItemSubCategory implements Serializable {
  private static final long serialVersionUID = 1542983808209044122L;

  @Id
  @Column(name = "id", nullable = false)
  private UUID id;

  @Column(name = "name", nullable = false, length = 64)
  private String name;

  @OneToMany(mappedBy = "subCategory")
  private Set<Item> items = new LinkedHashSet<>();

  @OneToMany(mappedBy = "subCategory")
  private Set<ItemSubCategoryL10n> itemSubCategoryL10ns = new LinkedHashSet<>();
}
