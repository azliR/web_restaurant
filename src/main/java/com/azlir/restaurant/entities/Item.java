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
@Table(name = "items")
public class Item implements Serializable {
    @Serial private static final long serialVersionUID = -1128940811846244696L;

    @Id
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private ItemCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_category_id")
    private ItemSubCategory subCategory;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Lob
    @Column(name = "picture")
    private String picture;

    @Column(name = "price", nullable = false, precision = 11, scale = 2)
    private BigDecimal price;

    @Column(name = "special_offer", nullable = false, precision = 11, scale = 2)
    private BigDecimal specialOffer;

    @Column(name = "description")
    private String description;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = false;

    @OneToMany(mappedBy = "item")
    private Set<ItemAddonCategory> itemAddonCategories = new LinkedHashSet<>();

    @OneToMany(mappedBy = "item")
    private Set<OrderDetail> orderDetails = new LinkedHashSet<>();
}
