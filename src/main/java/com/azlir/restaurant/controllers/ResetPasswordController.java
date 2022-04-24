package com.azlir.restaurant.controllers;

import com.azlir.restaurant.dtos.reset_password.PasswordReset;
import com.azlir.restaurant.services.framework.StoreAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.Instant;
import java.util.Locale;

@Controller
@RequestMapping("/reset-password")
public class ResetPasswordController {
  private final StoreAccountService storeAccountService;
  private final MessageSource messageSource;

  @Autowired
  public ResetPasswordController(
      MessageSource messageSource, StoreAccountService storeAccountService) {
    this.messageSource = messageSource;
    this.storeAccountService = storeAccountService;
  }

  @GetMapping
  public String viewPage(
      @RequestParam(name = "token", required = false) String token, Model model) {
    final var storeAccount = storeAccountService.findByToken(token);
    if (storeAccount == null) {
      model.addAttribute(
          "error", messageSource.getMessage("TOKEN_NOT_FOUND", new Object[] {}, Locale.ENGLISH));
    } else if (storeAccount.getStoreAdmin().getTokenExpiredAt().isBefore(Instant.now())) {
      model.addAttribute(
          "error", messageSource.getMessage("TOKEN_EXPIRED", new Object[] {}, Locale.ENGLISH));
    } else {
      model.addAttribute("token", storeAccount.getStoreAdmin().getTokenResetPassword());
    }
    return "reset-password";
  }

  @PostMapping
  public String resetPassword(
      @Valid @ModelAttribute("passwordReset") PasswordReset passwordReset,
      BindingResult result,
      RedirectAttributes attributes) {
    if (result.hasErrors()) {
      attributes.addFlashAttribute("passwordReset", passwordReset);
      return "redirect:/reset-password?token=" + passwordReset.getToken();
    }
    final var storeAccount = storeAccountService.findByToken(passwordReset.getToken());
    final var storeAdmin = storeAccount.getStoreAdmin();
    storeAdmin.setPassword(passwordReset.getPassword());
    storeAccount.setStoreAdmin(storeAdmin);
    storeAccountService.updatePassword(storeAccount);
    return "redirect:/login";
  }

  @ModelAttribute("passwordReset")
  public PasswordReset passwordReset() {
    return new PasswordReset();
  }
}
