package com.azlir.restaurant.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class UserDto implements Serializable {
  @NotBlank
  @Size(max = 64)
  private final String fullName;

  @NotBlank
  @Size(min = 10, max = 16)
  private final String phone;

  @NotBlank
  @Size(max = 2)
  private final String languageCode;
}
