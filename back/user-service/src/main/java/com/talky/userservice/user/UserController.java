package com.talky.userservice.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/users")
@RequiredArgsConstructor
class UserController {
  private final UserService userService;

  @GetMapping
  public Page<UserDto> getUsers() {
    return userService.getUsers(Pageable.ofSize(10));
  }


  @GetMapping( "/me")
  public Optional<UserDto> getCurrentUser() {
    return userService.getCurrentUserDto();
  }

  @PutMapping("/me")
  public UserDto updateProfile(@RequestBody UpdateUserRequestDto dto) {
    return userService.updateUser(dto);
  }

  @PostMapping("/ping")
  public void ping() {
    userService.updateUserLastConnection();
  }

  @PostMapping
  public UserDto createUser(@RequestBody CreateUserRequestDto dto) {
    return userService.createUser(dto);
  }
}
