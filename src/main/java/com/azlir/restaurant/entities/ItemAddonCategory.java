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
@Table(name = "item_addon_categories")
public class ItemAddonCategory implements Serializable {
  @Serial private static final long serialVersionUID = 6529876578345477874L;

  @Id
  @Column(name = "id", nullable = false, insertable = false, updatable = false)
  @Type(type = "org.hibernate.type.PostgresUUIDType")
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "item_id", nullable = false)
  private Item item;

  @Column(name = "name", nullable = false, length = 64)
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "is_multiple_choice", nullable = false)
  private Boolean isMultipleChoice = false;

  @OneToMany(mappedBy = "addonCategory")
  private Set<ItemAddon> itemAddons = new LinkedHashSet<>();
}
