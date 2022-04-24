package com.azlir.restaurant.entities.database;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(
    name = "coupons",
    indexes = {})
public class Coupon implements Serializable {
  private static final long serialVersionUID = -4296462492576847321L;

  @Id
  @Column(name = "id", nullable = false)
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "inserted_by", nullable = false)
  private StoreAdmin insertedBy;
}
