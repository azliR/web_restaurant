package com.azlir.restaurant.controllers;

import com.azlir.restaurant.dtos.UserDto;
import com.azlir.restaurant.entities.Role;
import com.azlir.restaurant.entities.User;
import com.azlir.restaurant.exceptions.NotFoundException;
import com.azlir.restaurant.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping(value = "/all")
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @GetMapping(value = "/{id}")
  public User getUserById(@PathVariable("id") @NotBlank String id) {
    return userService
        .findById(UUID.fromString(id))
        .orElseThrow(() -> new NotFoundException("User dengan id " + id + " tidak ditemukan!"));
  }

  @PostMapping
  public User addUser(@Valid @RequestBody UserDto user) {
    return userService.save(User.fromUserDto(user, Role.builder().name("ROLE_USER").build()));
  }

  //  @PutMapping(value = "/{id}")
  //  public User updateUser(@PathVariable("id") @NotBlank String id, @Valid @RequestBody UserDto
  // newUser) {
  //    User user =
  //        userService
  //            .findById(UUID.fromString(id))
  //            .orElseThrow(() -> new NotFoundException("User dengan id " + id + " tidak
  // ditemukan!"));
  //    user.setFullName(newUser.getFullName());
  //    user.setPhone(newUser.getPhone());
  //    user.setLanguageCode(newUser.getLanguageCode());
  //    user.setRole(newUser.getRole());
  //    return userService.save(user);
  //  }

  @DeleteMapping(value = "/{id}")
  public String deleteUser(@PathVariable("id") @NotBlank String id) {
    User user =
        userService
            .findById(UUID.fromString(id))
            .orElseThrow(() -> new NotFoundException("User dengan id " + id + " tidak ditemukan!"));
    userService.deleteById(user.getId());
    return "User dengan ID :" + id + " berhasil dihapus";
  }
}
