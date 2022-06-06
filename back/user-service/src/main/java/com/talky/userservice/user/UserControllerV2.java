package com.talky.userservice.user;

import com.talky.commons.users.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v2/users")
@RequiredArgsConstructor
public class UserControllerV2 {
  private final UserService userService;

  @Operation(description = "Get the profile of a user")
  @GetMapping( "/{id}")
  public Optional<ProfileDto> getUserById(@PathVariable UUID id) {
    return userService.getProfileById(id);
  }
}
