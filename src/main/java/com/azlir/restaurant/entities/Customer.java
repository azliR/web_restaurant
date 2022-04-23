package com.azlir.restaurant.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(
    name = "customers",
    indexes = {@Index(name = "uk_customers_on_phone", columnList = "phone", unique = true)})
public class Customer implements Serializable {
  @Serial private static final long serialVersionUID = 243997900109674921L;

  @Id
  @Column(name = "id", nullable = false)
  private UUID id;

  @Column(name = "full_name", nullable = false, length = 64)
  private String fullName;

  @Column(name = "phone", nullable = false, length = 16)
  private String phone;

  @Column(name = "language_code", nullable = false, length = 2)
  private String languageCode;

  @Column(name = "created_at", nullable = false)
  private Instant createdAt;
}
