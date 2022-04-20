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
@Table(name = "tables")
public class ReservationTable implements Serializable {
    @Serial private static final long serialVersionUID = -7095213014257988289L;

    @Id
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
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
