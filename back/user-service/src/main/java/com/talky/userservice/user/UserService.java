package com.talky.userservice.user;

import com.talky.commons.auth.AuthenticationHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
class UserService implements IUser {
  private final UserRepository userRepository;
  private final AuthenticationHelper authenticationHelper;

  public UserDto toDto(User user) {
    var dto = new UserDto();
    dto.setId(user.getId());
    dto.setDisplayedName(user.getDisplayedName());
    dto.setUsername(user.getUsername());
    dto.setProfilePicture(user.getProfilePicture());
    return dto;
  }

  UserDto createUser(CreateUserRequestDto dto) {
    var user = new User();
    user.setUsername(dto.getUsername());
    user.setDisplayedName(dto.getDisplayedName() == null ? dto.getUsername() : dto.getDisplayedName());
    user.setProfilePicture(dto.getProfilePicture());
    var u = userRepository.save(user);
    return toDto(u);
  }

  Page<UserDto> getUsers(Pageable pageable) {
    var users = userRepository.findAll(pageable);
    return users.map(this::toDto);
  }

  Optional<String> getCurrentUser() {
    return authenticationHelper.getCurrentUserId();
  }

}
