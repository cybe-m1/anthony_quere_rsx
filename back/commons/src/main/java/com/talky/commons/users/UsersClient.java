package com.talky.commons.users;

import com.talky.commons.auth.IAuthentication;
import com.talky.commons.client.AbstractTalkyClient;
import com.talky.commons.client.AbstractTalkyClientConfig;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
class UsersClient extends AbstractTalkyClient implements IUsers {
  public UsersClient(IAuthentication authentication, UsersClientConfiguration config) {
    super(authentication, config);
  }

  public UserDto getCurentUser() {
    return buildWebClient()
      .get()
      .uri("/api/v1/users/me")
      .retrieve()
      .bodyToMono(UserDto.class)
      .block();
  }

  public UserDto getUserById(UUID id) {
    return buildWebClient()
      .get()
      .uri(uriBuilder -> uriBuilder.path("/api/v1/users/{id}").build(id))
      .retrieve()
      .bodyToMono(UserDto.class)
      .block();
  }

  @Override
  public Collection<String> getUserDevices(UUID userId) {
    var devices =  buildWebClient()
      .get()
      .uri(uriBuilder -> uriBuilder.path("/api/v1/devices/user/{userId}").build(userId))
      .retrieve()
      .bodyToMono(DeviceDto[].class)
      .block();

    if (devices == null) {
      return List.of();
    }

    return Arrays.stream(devices).map(DeviceDto::getDeviceId).toList();
  }

}
