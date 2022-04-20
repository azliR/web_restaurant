package com.azlir.restaurant.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "postcodes")
public class Postcode implements Serializable {
    @Serial private static final long serialVersionUID = -8327381853580947527L;

    @Id
    @Column(name = "postcode", nullable = false, length = 5)
    private String postcode;

    @Column(name = "city", nullable = false, length = 128)
    private String city;

    @Column(name = "state", nullable = false, length = 128)
    private String state;

    @Column(name = "country", nullable = false, updatable = false, length = 56)
    private String country;

}
