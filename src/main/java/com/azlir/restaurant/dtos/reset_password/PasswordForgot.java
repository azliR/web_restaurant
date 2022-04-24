package com.azlir.restaurant.dtos.reset_password;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class PasswordForgot {
  @NotEmpty(message = "{EMAIL_REQUIRED}")
  @Email(message = "{NOT_VALID_EMAIL}")
  private String email;
}