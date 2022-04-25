package com.azlir.restaurant.controllers;

import com.azlir.restaurant.dtos.reset_password.Mail;
import com.azlir.restaurant.dtos.reset_password.PasswordForgot;
import com.azlir.restaurant.services.framework.EmailService;
import com.azlir.restaurant.services.framework.StoreAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/forgot-password")
public class ForgotPasswordController {
  private final StoreAccountService storeAccountService;
  private final MessageSource messageSource;
  private final EmailService emailService;

  @Autowired
  public ForgotPasswordController(
      StoreAccountService storeAccountService,
      MessageSource messageSource,
      EmailService emailService) {
    this.storeAccountService = storeAccountService;
    this.messageSource = messageSource;
    this.emailService = emailService;
  }

  @GetMapping
  public String viewPage() {
    return "forgot-password";
  }

  @PostMapping
  public String processPasswordForgot(
      @NotEmpty @ModelAttribute("passwordForgot") PasswordForgot passwordForgot,
      BindingResult result,
      Model model,
      RedirectAttributes attributes,
      HttpServletRequest request) {
    if (result.hasErrors()) {
      return "forgot-password";
    }
    final var storeAccount = storeAccountService.findByEmail(passwordForgot.getEmail());
    if (storeAccount == null) {
      model.addAttribute(
          "emailError",
          messageSource.getMessage("EMAIL_NOT_FOUND", new Object[] {}, Locale.ENGLISH));
      return "forgot-password";
    }
    final var storeAdmin = storeAccount.getStoreAdmin();
    storeAdmin.setTokenResetPassword(UUID.randomUUID().toString());
    storeAdmin.setTokenExpiredAt(Instant.now().plus(Duration.ofMinutes(30)));
    storeAccountService.saveAdmin(storeAccount, storeAdmin, request);

    if (storeAdmin.getTokenResetPassword() == null) {
      model.addAttribute(
          "tokenError",
          messageSource.getMessage("TOKEN_NOT_SAVED", new Object[] {}, Locale.ENGLISH));
      return "forgot-password";
    }
    final var mail = new Mail();
    mail.setFrom("no-reply@foodhub.com");
    mail.setTo(storeAdmin.getEmail());
    mail.setSubject("Password reset request");

    final Map<String, Object> mailModel = new HashMap<>();
    mailModel.put("token", storeAdmin.getTokenResetPassword());
    mailModel.put("storeAdmin", storeAdmin);
    mailModel.put("signature", "rizalhadiyansah@gmail.com");

    final var url =
        request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
    mailModel.put("resetUrl", url + "/reset-password?token=" + storeAdmin.getTokenResetPassword());
    mail.setModel(mailModel);

    emailService.send(mail);
    attributes.addFlashAttribute(
        "success",
        messageSource.getMessage("PASSWORD_RESET_TOKEN_SENT", new Object[] {}, Locale.ENGLISH));
    return "redirect:/forgot-password";
  }

  @ModelAttribute("passwordForgot")
  public PasswordForgot passwordForgot() {
    return new PasswordForgot();
  }
}
