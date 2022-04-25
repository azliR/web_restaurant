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
public class ItemCategoryL10nId implements Serializable {
  private static final long serialVersionUID = -2439782013465386360L;

  @Column(name = "category_id", nullable = false)
  private UUID categoryId;

  @Column(name = "language_code", nullable = false, length = 2)
  private String languageCode;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    ItemCategoryL10nId entity = (ItemCategoryL10nId) o;
    return Objects.equals(this.languageCode, entity.languageCode)
        && Objects.equals(this.categoryId, entity.categoryId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(languageCode, categoryId);
  }
}
