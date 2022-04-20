package com.azlir.restaurant.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serial;
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
@Table(name = "roles")
public class Role implements Serializable {
  @Serial private static final long serialVersionUID = -9110218751713565798L;

  @NotBlank
  @Id
  @Column(name = "name", nullable = false, length = 16)
  private String name;

  @OneToMany(mappedBy = "role")
  private Set<User> users = new LinkedHashSet<>();
}
