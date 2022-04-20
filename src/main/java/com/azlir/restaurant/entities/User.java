package com.azlir.restaurant.entities;

import com.azlir.restaurant.dtos.UserDto;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
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
@Table(
    name = "users",
    indexes = {@Index(name = "phone", columnList = "phone", unique = true)})
public class User implements Serializable {
  @Serial private static final long serialVersionUID = -6987047642711034886L;

  @Id
  @GeneratedValue
  @Column(name = "id", nullable = false, insertable = false, updatable = false)
  @Type(type = "org.hibernate.type.PostgresUUIDType")
  private UUID id;

  @NotBlank
  @Size(max = 64)
  @Column(name = "full_name", nullable = false, length = 64)
  private String fullName;

  @NotBlank
  @Size(min = 10, max = 16)
  @Column(name = "phone", nullable = false, length = 16)
  private String phone;

  @NotBlank
  @Size(max = 2)
  @Column(name = "language_code", nullable = false, length = 2)
  private String languageCode;

  @ManyToOne(
      fetch = FetchType.LAZY,
      cascade = {CascadeType.ALL})
  @JoinColumn(name = "role", nullable = false)
  private Role role;

  @Column(
      name = "created_at",
      nullable = false,
      updatable = false,
      insertable = false,
      columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP")
  private Instant createdAt;

  @ManyToMany
  @JoinTable(
      name = "coupon_users",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "coupon_id"))
  private Set<Coupon> coupons = new LinkedHashSet<>();

  @OneToMany(mappedBy = "insertedBy")
  private Set<Coupon> inserted_coupons = new LinkedHashSet<>();

  @OneToMany(mappedBy = "user")
  private Set<Store> stores = new LinkedHashSet<>();

  @OneToMany(mappedBy = "user")
  private Set<Order> orders = new LinkedHashSet<>();

  public static User fromUserDto(UserDto userDto, Role role) {
    return User.builder()
        .fullName(userDto.getFullName())
        .phone(userDto.getPhone())
        .languageCode(userDto.getLanguageCode())
        .build();
  }
}
