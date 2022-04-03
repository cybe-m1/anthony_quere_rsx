package com.talky.commons.users;

import com.talky.commons.auth.IAuthentication;
import com.talky.commons.client.AbstractTalkyClient;
import com.talky.commons.client.AbstractTalkyClientConfig;
import org.springframework.stereotype.Component;

import java.util.UUID;

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

}
