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
@Table(name = "item_sub_category_l10ns")
public class ItemSubCategoryL10n implements Serializable {
  private static final long serialVersionUID = -826634579684291576L;
  @EmbeddedId private ItemSubCategoryL10nId id;

  @MapsId("subCategoryId")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "sub_category_id", nullable = false)
  private ItemSubCategory subCategory;

  @Column(name = "name", nullable = false, length = 64)
  private String name;
}
