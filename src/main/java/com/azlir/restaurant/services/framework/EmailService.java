package com.azlir.restaurant.services.framework;

import com.azlir.restaurant.dtos.reset_password.Mail;

public interface EmailService {
  void send(Mail mail);
}
