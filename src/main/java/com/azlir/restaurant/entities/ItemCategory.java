package com.azlir.restaurant.entities;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serial;
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
    @Serial private static final long serialVersionUID = -1780665955241691252L;

    @Id
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    private UUID id;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @OneToMany(mappedBy = "itemCategory")
    private Set<ItemCategoryL10n> itemCategoryL10ns = new LinkedHashSet<>();

    @OneToMany(mappedBy = "category")
    private Set<Item> items = new LinkedHashSet<>();
}
