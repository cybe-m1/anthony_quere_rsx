package com.talky.userservice.user;

import com.talky.commons.assets.dto.AssetTemporaryLinkResponseDto;
import com.talky.commons.users.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
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
  public Page<UserDto> getUsers(@ParameterObject Pageable pageable) {
    return userService.getUsers(pageable);
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

  @Operation(description = "Generate an upload link for a profile picture. The link can be used by a form to upload a profile picture. The generated link is available for 30 minutes.")
  @GetMapping("/profile-picture/upload")
  AssetTemporaryLinkResponseDto getAssetUploadLink(@RequestParam String extension) {
    return userService.getProfilePictureUploadLink(extension);
  }
}
