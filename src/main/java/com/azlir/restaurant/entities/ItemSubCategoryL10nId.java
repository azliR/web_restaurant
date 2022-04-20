package com.azlir.restaurant.entities;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serial;
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
    @Serial
    private static final long serialVersionUID = -1387556006950575685L;

    @Column(name = "sub_category_id", nullable = false, updatable = false)
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID categoryId;
    @Column(name = "language_code", nullable = false, length = 2)
    @Type(type="org.hibernate.type.CharacterType")
    private String languageCode;

    @Override
    public int hashCode() {
        return Objects.hash(languageCode, categoryId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ItemSubCategoryL10nId entity = (ItemSubCategoryL10nId) o;
        return Objects.equals(this.languageCode, entity.languageCode) &&
                Objects.equals(this.categoryId, entity.categoryId);
    }
}