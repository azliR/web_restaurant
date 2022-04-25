package com.azlir.restaurant.entities.database;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "postcodes")
public class Postcode implements Serializable {
  private static final long serialVersionUID = 159372807839170408L;

  @Id
  @Column(name = "postcode", nullable = false, length = 5)
  private String id;

  @Column(name = "city", nullable = false, length = 128)
  private String city;

  @Column(name = "state", nullable = false, length = 128)
  private String state;

  @Column(name = "country", nullable = false, length = 56)
  private String country;

  @OneToMany(mappedBy = "postcode")
  private Set<Store> stores = new LinkedHashSet<>();
}
