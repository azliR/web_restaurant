package com.azlir.restaurant.entities;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "item_addons")
public class ItemAddon implements Serializable {
    @Serial private static final long serialVersionUID = -1122607968342313652L;

    @Id
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "addon_category_id")
    private ItemAddonCategory addonCategory;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Column(name = "price", nullable = false, precision = 11, scale = 2)
    private BigDecimal price;

    @OneToMany(mappedBy = "addon")
    private Set<OrderDetailAddon> orderDetailAddons = new LinkedHashSet<>();
}
