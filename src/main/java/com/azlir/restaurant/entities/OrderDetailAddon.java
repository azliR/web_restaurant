package com.azlir.restaurant.entities;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "order_detail_addons")
public class OrderDetailAddon implements Serializable {
    @Serial private static final long serialVersionUID = -2038763703857421287L;

    @Id
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_detail_id")
    private OrderDetail orderDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "addon_id")
    private ItemAddon addon;

    @Column(name = "addon_name", nullable = false, length = 64)
    private String addonName;

    @Column(name = "quantity", nullable = false)
    private Boolean quantity = false;

    @Column(name = "price", nullable = false, precision = 11, scale = 2)
    private BigDecimal price;
}
