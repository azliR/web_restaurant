package com.azlir.restaurant.entities.database;

import lombok.*;

import javax.persistence.*;
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
@Table(name = "reservation_tables")
public class ReservationTable implements Serializable {
  private static final long serialVersionUID = 1957187605789557989L;

  @Id
  @Column(name = "id", nullable = false)
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "store_id", nullable = false)
  private Store store;

  @Column(name = "name", nullable = false, length = 64)
  private String name;

  @Column(name = "max_person", nullable = false)
  private Boolean maxPerson = false;

  @Column(name = "total_person", nullable = false)
  private Boolean totalPerson = false;

  @Column(name = "book_price", nullable = false, precision = 11, scale = 2)
  private BigDecimal bookPrice;

  @OneToMany(mappedBy = "table")
  private Set<Order> orders = new LinkedHashSet<>();
}
