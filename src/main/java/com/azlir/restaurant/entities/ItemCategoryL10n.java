package com.azlir.restaurant.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
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
    @Serial
    private static final long serialVersionUID = 8483241291080471824L;

    @EmbeddedId
    private ItemCategoryL10nId id;
    @MapsId("categoryId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private ItemCategory itemCategory;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

}