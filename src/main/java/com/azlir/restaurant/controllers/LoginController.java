package com.azlir.restaurant.controllers;

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
import java.util.Locale;

@Controller
@RequestMapping("/login")
public class LoginController {
  private final StoreAccountService storeAccountService;
  private final MessageSource messageSource;

  @Autowired
  public LoginController(StoreAccountService storeAccountService, MessageSource messageSource) {
    this.storeAccountService = storeAccountService;
    this.messageSource = messageSource;
  }

  @GetMapping
  public String viewPage() {
    return "login";
  }

  @PostMapping
  public String loginAdmin(
      @ModelAttribute("storeAdmin") StoreAdmin storeAdmin,
      HttpServletRequest request,
      BindingResult result,
      Model model,
      RedirectAttributes attributes) {
    System.out.println("LoginController.login");
    if (result.hasErrors()) {
      return "login";
    }
    final var storeAccount = storeAccountService.login(storeAdmin, request);
    if (storeAccount == null) {
      model.addAttribute(
          "error", messageSource.getMessage("ERROR_LOGIN", new Object[] {}, Locale.ENGLISH));
      return "login";
    }
    return "redirect:/index";
  }

  @ModelAttribute("storeAdmin")
  public StoreAdmin storeAdmin() {
    return new StoreAdmin();
  }
}
