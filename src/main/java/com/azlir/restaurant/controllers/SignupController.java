package com.azlir.restaurant.controllers;

import com.azlir.restaurant.entities.database.StoreAccount;
import com.azlir.restaurant.entities.database.StoreAdmin;
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
import javax.validation.Valid;
import java.util.Locale;

@Controller
@RequestMapping("/signup")
public class SignupController {
  private final StoreAccountService storeAccountService;
  private final MessageSource messageSource;

  @Autowired
  public SignupController(StoreAccountService storeAccountService, MessageSource messageSource) {
    this.storeAccountService = storeAccountService;
    this.messageSource = messageSource;
  }

  @GetMapping
  public String viewPage() {
    return "signup";
  }

  @PostMapping
  public String saveUser(
      @Valid @ModelAttribute("storeAccount") StoreAccount storeAccount,
      @Valid @ModelAttribute("storeAdmin") StoreAdmin storeAdmin,
      HttpServletRequest request,
      BindingResult result,
      Model model,
      RedirectAttributes attributes) {
    if (result.hasErrors()) {
      return "signup";
    }
    if (storeAccountService.findByEmail(storeAdmin.getEmail()) != null) {
      model.addAttribute(
          "error", messageSource.getMessage("EMAIL_EXISTS", new Object[] {}, Locale.ENGLISH));
      return "signup";
    }
    storeAccount = storeAccountService.saveAdmin(storeAccount, storeAdmin, request);
    if (storeAccount == null) {
      model.addAttribute(
          "error", messageSource.getMessage("EMAIL_NOT_SAVED", new Object[] {}, Locale.ENGLISH));
      return "signup";
    }
    attributes.addFlashAttribute(
        "success", messageSource.getMessage("EMAIL_SAVED", new Object[] {}, Locale.ENGLISH));
    return "redirect:/signup";
  }

  @ModelAttribute("storeAccount")
  public StoreAccount storeAccount() {
    return new StoreAccount();
  }

  @ModelAttribute("storeAdmin")
  public StoreAdmin storeAdmin() {
    return new StoreAdmin();
  }
}
