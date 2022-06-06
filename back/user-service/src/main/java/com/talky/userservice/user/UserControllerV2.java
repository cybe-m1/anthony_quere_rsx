package com.talky.userservice.user;

import com.talky.commons.users.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

  @Operation(description = "Search users with pagination")
  @GetMapping
  public Page<UserDto> searchUsers(@ParameterObject Pageable pageable, @RequestParam String search) {
    return userService.searchUsers(search, pageable);
  }
}
