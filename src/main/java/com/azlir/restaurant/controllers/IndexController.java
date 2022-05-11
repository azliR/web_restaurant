package com.azlir.restaurant.controllers;

import com.azlir.restaurant.entities.database.Item;
import com.azlir.restaurant.entities.database.ItemCategory;
import com.azlir.restaurant.services.framework.ItemService;
import com.azlir.restaurant.services.framework.StoreAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class IndexController {
  private final ItemService itemService;
  private final StoreAccountService storeAdminService;

  @Autowired
  public IndexController(ItemService itemService, StoreAccountService storeAdminService) {
    this.itemService = itemService;
    this.storeAdminService = storeAdminService;
  }

  @GetMapping("/")
  public String index(Model model, HttpServletRequest request) {
    final var storeAccount =
        storeAdminService.findByEmail(request.getSession().getAttribute("username").toString());
    model.addAttribute(
        "item", itemService.getItemsByStoreId(storeAccount.getStoreAdmin().getStore().getId()));
    return "index";
  }

  @GetMapping(value = "/create")
  public String create(Model model) {
    model.addAttribute("itemCategory", new ItemCategory());
    model.addAttribute("item", new Item());
    return "form-item";
  }

  @PostMapping(value = "/create")
  public String addItem(
      Item item, ItemCategory itemCategory, Model model, HttpServletRequest request) {
    model.addAttribute("itemCategory", itemCategory);
    item.setCategory(itemCategory);
    model.addAttribute("item", itemService.save(item, request));
    return "redirect:/";
  }

  @GetMapping(value = "/edit/{id}")
  public String editItem(@PathVariable String id, Model model) {
    model.addAttribute("item", itemService.findById(UUID.fromString(id)));
    return "form-item";
  }

  @GetMapping("/logout")
  public String getLogoutPage(HttpServletRequest request, HttpServletResponse response) {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null)
      new SecurityContextLogoutHandler().logout(request, response, authentication);

    return "redirect:/login";
  }

  @GetMapping(value = "/delete/{id}")
  public String deleteItem(@PathVariable String id) {
    itemService.deleteById(UUID.fromString(id));
    return "redirect:/";
  }

  @ModelAttribute("item")
  public Item item() {
    return new Item();
  }

  @ModelAttribute("itemCategory")
  public ItemCategory itemCategory() {
    return new ItemCategory();
  }
}
