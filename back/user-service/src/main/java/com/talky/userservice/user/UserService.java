package com.talky.userservice.user;

import com.talky.commons.assets.IAssets;
import com.talky.commons.auth.AuthenticationHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Optional;


@Service
@RequiredArgsConstructor
class UserService implements IUser {
  private final UserRepository userRepository;
  private final AuthenticationHelper authenticationHelper;
  private final IAssets assets;
  private final UserMapper userMapper;

  private String resolveProfilePicture(User user) {
    if (user.getProfilePicture() != null && !user.getProfilePicture().isEmpty()) {
      return assets.getTemporaryLink("user-profile-dev", user.getProfilePicture()).getUrl().toString();
    }
    return user.getDefaultProfilePicture();
  }

  public UserDto toDto(User user) {
    var dto = new UserDto();
    dto.setId(user.getId());
    dto.setDisplayedName(user.getDisplayedName());
    dto.setLastSeen(user.getLastSeen());
    dto.setProfilePicture(resolveProfilePicture(user));
    return dto;
  }

  UserDto createUser(CreateUserRequestDto dto) {
    var currentUserId = authenticationHelper.getCurrentUserId().orElseThrow(() -> new RuntimeException("Authentication is required"));
    var user = userMapper.createUserRequestDtoToUser(dto);
    user.setAccountId(currentUserId);
    user.setLastSeen(LocalDateTime.now());
    return toDto(userRepository.save(user));
  }

  UserDto updateUser(UpdateUserRequestDto dto) {
    var currentUser = getCurrentUser().orElseThrow(() -> new RuntimeException("Authentication is required"));
    userMapper.updateUser(dto, currentUser);
    return toDto(currentUser);
  }

  Page<UserDto> getUsers(Pageable pageable) {
    var users = userRepository.findAll(pageable);
    return users.map(this::toDto);
  }

  void updateUserLastConnection() {
    var currentUser = getCurrentUser().orElseThrow(() -> new RuntimeException("Authentication is required"));
    currentUser.setLastSeen(LocalDateTime.now());
    userRepository.save(currentUser);
  }

  Optional<UserDto> getCurrentUserDto() {
    return getCurrentUser().map(this::toDto);
  }

  Optional<User> getCurrentUser() {
    return authenticationHelper.getCurrentUserId().map(id -> userRepository.findByAccountId(id).orElseThrow(() -> new RuntimeException("User not found")));
  }

}
