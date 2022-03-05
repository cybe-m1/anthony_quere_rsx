package com.talky.userservice.user;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/users")
@RequiredArgsConstructor
class UserController {
  private final UserService userService;

  @GetMapping
  public Page<UserDto> getUsers() {
    return userService.getUsers(Pageable.ofSize(10));
  }

  @PostMapping
  public UserDto postUser(@RequestBody CreateUserRequestDto dto) {
    return userService.createUser(dto);
  }
}
