package com.azlir.restaurant.entities.database;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Embeddable
public class ItemSubCategoryL10nId implements Serializable {
  private static final long serialVersionUID = 3956973767663430593L;

  @Column(name = "sub_category_id", nullable = false)
  private UUID subCategoryId;

  @Column(name = "language_code", nullable = false, length = 2)
  private String languageCode;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    ItemSubCategoryL10nId entity = (ItemSubCategoryL10nId) o;
    return Objects.equals(this.subCategoryId, entity.subCategoryId)
        && Objects.equals(this.languageCode, entity.languageCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(subCategoryId, languageCode);
  }
}
