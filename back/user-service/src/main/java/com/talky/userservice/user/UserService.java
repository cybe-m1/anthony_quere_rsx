package com.talky.userservice.user;

import com.talky.commons.assets.IAssets;
import com.talky.commons.assets.dto.AssetTemporaryLinkResponseDto;
import com.talky.commons.auth.AuthenticationHelper;
import com.talky.commons.exceptions.TalkyNotFoundException;
import com.talky.commons.exceptions.TalkyUnauthorizedException;
import com.talky.commons.social.ISocial;
import com.talky.commons.users.UserDto;
import com.talky.userservice.devices.IDevices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
class UserService implements IUser {
  private final UserRepository userRepository;
  private final AuthenticationHelper authenticationHelper;
  private final IAssets assets;
  private final UserMapper userMapper;
  private final IDevices devices;
  private final ISocial social;

  private final static String ASSET_BUCKET_DIR = "user-profile-dev";

  AssetTemporaryLinkResponseDto getProfilePictureUploadLink(String extension) {
    return assets.getUploadTemporaryLink(ASSET_BUCKET_DIR, extension);
  }

  private String resolveProfilePicture(User user) {
    String profilePicture = user.getDefaultProfilePicture();
    if (user.getProfilePicture() != null && !user.getProfilePicture().isEmpty() && !user.getProfilePicture().startsWith("http")) {

      try {
        profilePicture = assets.getTemporaryLink(ASSET_BUCKET_DIR, user.getProfilePicture()).getUrl().toString();
      } catch (WebClientResponseException ex) {
        log.warn("Fail to load profile picture with name : {}", user.getProfilePicture());
      }
    }
    return profilePicture;
  }

  public UserDto toDto(User user) {
    var dto = new UserDto();
    dto.setId(user.getId());
    dto.setDisplayedName(user.getDisplayedName());
    dto.setLastSeen(user.getLastSeen());
    dto.setProfilePicture(resolveProfilePicture(user));
    return dto;
  }

  public ProfileDto toProfileDto(User user) {

    var dto = new ProfileDto();
    dto.setId(user.getId());
    dto.setDisplayedName(user.getDisplayedName());
    dto.setLastSeen(user.getLastSeen());
    dto.setProfilePicture(resolveProfilePicture(user));
    dto.setAreFriends(social.findFriendshipByFriend(user.getId()) != null);
    return dto;
  }

  UserDto createUser(CreateUserRequestDto dto) {
    var currentUserId = authenticationHelper.getCurrentUserId().orElseThrow(() -> new TalkyUnauthorizedException("Authentication is required"));
    var user = userMapper.createUserRequestDtoToUser(dto);
    user.setAccountId(currentUserId);
    user.setLastSeen(LocalDateTime.now());
    return toDto(userRepository.save(user));
  }

  UserDto updateUser(UpdateUserRequestDto dto) {
    var currentUser = getCurrentUser().orElseThrow(() -> new TalkyUnauthorizedException("Authentication is required"));
    if (dto.getDisplayedName() != null && !dto.getDisplayedName().isEmpty()) {
      currentUser.setDisplayedName(dto.getDisplayedName());
    }
    if (dto.getProfilePicture() != null && !dto.getProfilePicture().isEmpty()) {
      currentUser.setProfilePicture(dto.getProfilePicture());
    }
    return toDto(userRepository.save(currentUser));
  }

  Page<UserDto> getUsers(Pageable pageable) {
    var users = userRepository.findAll(pageable);
    return users.map(this::toDto);
  }

  void updateUserLastConnection(UserPingDto dto) {
    var currentUser = getCurrentUser().orElseThrow(() -> new TalkyUnauthorizedException("Authentication is required"));
    currentUser.setLastSeen(LocalDateTime.now());

    if (dto.getDeviceId() != null) {
      devices.assignDeviceToUser(dto.getDeviceId(), currentUser.getId());
    }

    userRepository.save(currentUser);
  }

  Optional<UserDto> getUserById(UUID id) {
    return userRepository.findById(id).map(this::toDto);
  }

  Optional<ProfileDto> getProfileById(UUID id) {
    return userRepository.findById(id).map(this::toProfileDto);
  }

  Optional<UserDto> getCurrentUserDto() {
    return getCurrentUser().map(this::toDto);
  }

  Optional<User> getCurrentUser() {
    return authenticationHelper.getCurrentUserId().map(id -> userRepository.findByAccountId(id).orElseThrow(() -> new TalkyNotFoundException("User not found")));
  }

}
