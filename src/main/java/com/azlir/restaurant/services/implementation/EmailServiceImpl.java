package com.azlir.restaurant.services.implementation;

import com.azlir.restaurant.dtos.reset_password.Mail;
import com.azlir.restaurant.services.framework.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import java.nio.charset.StandardCharsets;

@Service
public class EmailServiceImpl implements EmailService {
  private final JavaMailSender mailSender;
  private final SpringTemplateEngine templateEngine;

  @Autowired
  public EmailServiceImpl(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
    this.mailSender = mailSender;
    this.templateEngine = templateEngine;
  }

  @Override
  public void send(Mail mail) {
    try {
      final var mimeMessage = mailSender.createMimeMessage();
      final var helper =
          new MimeMessageHelper(
              mimeMessage,
              MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
              StandardCharsets.UTF_8.name());
      final var context = new Context();
      context.setVariables(mail.getModel());

      final var html = templateEngine.process("email/email-template", context);
      helper.setTo(mail.getTo());
      helper.setFrom(mail.getFrom());
      helper.setSubject(mail.getSubject());
      helper.setText(html, true);

      mailSender.send(mimeMessage);
    } catch (MessagingException e) {
      e.printStackTrace();
    }
  }
}
