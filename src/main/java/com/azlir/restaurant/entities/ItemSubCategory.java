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
@Table(name = "item_sub_categories")
public class ItemSubCategory implements Serializable {
    @Serial private static final long serialVersionUID = 7711999338702796165L;

    @Id
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    private UUID id;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @OneToMany(mappedBy = "subCategory")
    private Set<Item> items = new LinkedHashSet<>();
}
