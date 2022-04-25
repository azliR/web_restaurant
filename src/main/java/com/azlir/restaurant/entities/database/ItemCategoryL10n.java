package com.azlir.restaurant.entities.database;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "item_category_l10ns")
public class ItemCategoryL10n implements Serializable {
  private static final long serialVersionUID = 7480625659980782571L;
  @EmbeddedId private ItemCategoryL10nId id;

  @MapsId("categoryId")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "category_id", nullable = false)
  private ItemCategory category;

  @Column(name = "name", nullable = false, length = 64)
  private String name;
}
