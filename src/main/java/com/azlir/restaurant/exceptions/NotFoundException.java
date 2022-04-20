package com.azlir.restaurant.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
  @Serial private static final long serialVersionUID = 1L;
  private String message;

  public NotFoundException(String message) {
    this.message = message;
  }
}
