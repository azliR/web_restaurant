package com.azlir.restaurant.controllers;

import com.azlir.restaurant.entities.database.Item;
import com.azlir.restaurant.entities.database.ItemCategory;
import com.azlir.restaurant.services.framework.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class IndexController {
  private final ItemService itemService;

  @Autowired
  public IndexController(ItemService itemService) {
    this.itemService = itemService;
  }

  @GetMapping("/")
  public String index(Model model) {
    model.addAttribute("item", itemService.getAllItems());
    return "index";
  }

  @GetMapping(value = "/create")
  public String create(Model model) {
    model.addAttribute("itemCategory", new ItemCategory());
    model.addAttribute("item", new Item());
    return "form_item";
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
    return "form_item";
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
