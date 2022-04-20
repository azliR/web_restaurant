package com.azlir.restaurant.controllers;

import com.azlir.restaurant.entities.User;
import com.azlir.restaurant.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Controller
@AllArgsConstructor
public class UserWebController {
  private UserService userService;

  @GetMapping("/")
  public String index(Model model) {
    model.addAttribute("user", userService.getAllUsers());
    return "index";
  }

  @GetMapping(value = "/create")
  public String create(Model model) {
    model.addAttribute("user", new User());
    return "form_user";
  }

  @PostMapping(value = "/create")
  public String addUser(Model model, User user) {
    model.addAttribute("user", userService.save(user));
    return "redirect:/";
  }

  @GetMapping(value = "/edit/{id}")
  public String editForm(@PathVariable String id, Model model) {
    model.addAttribute("user", userService.findById(UUID.fromString(id)));
    return "form_user";
  }

  @GetMapping(value = "/hapus/{id}")
  public String deleteUser(@PathVariable String id) {
    userService.deleteById(UUID.fromString(id));
    return "redirect:/";
  }
}
