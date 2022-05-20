package com.talky.userservice.user;

import com.talky.commons.users.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/users")
@RequiredArgsConstructor
class UserController {
  private final UserService userService;

  @Operation(description = "List users")
  @GetMapping
  public Page<UserDto> getUsers() {
    return userService.getUsers(Pageable.ofSize(10));
  }

  @Operation(description = "Get the current user profile")
  @GetMapping( "/me")
  public Optional<UserDto> getCurrentUser() {
    return userService.getCurrentUserDto();
  }

  @Operation(description = "Get the profile of a user")
  @GetMapping( "/{id}")
  public Optional<UserDto> getUserById(@PathVariable UUID id) {
    return userService.getUserById(id);
  }

  @Operation(description = "Update the current user")
  @PutMapping("/me")
  public UserDto updateProfile(@RequestBody UpdateUserRequestDto dto) {
    return userService.updateUser(dto);
  }

  @Operation(description = "Update the last login of a user. Can also be used to register a device")
  @PostMapping("/ping")
  public void ping() {
    userService.updateUserLastConnection();
  }

  @Operation(description = "Create a new user that matches the user that made the request.")
  @PostMapping
  public UserDto createUser(@RequestBody CreateUserRequestDto dto) {
    return userService.createUser(dto);
  }
}
