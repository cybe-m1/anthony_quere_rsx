package com.talky.commons.social;

import com.talky.commons.auth.IAuthentication;
import com.talky.commons.client.AbstractTalkyClient;
import com.talky.commons.client.AbstractTalkyClientConfig;
import com.talky.commons.users.UserDto;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
class SocialClient extends AbstractTalkyClient implements ISocial  {
  public SocialClient(IAuthentication authentication, SocialClientConfiguration config) {
    super(authentication, config);
  }

  @Override
  public FriendshipDto findFriendshipByFriend(UUID friend) {
    return buildWebClient()
      .get()
      .uri(uriBuilder -> uriBuilder.path("/api/v1/friends/friend/{friendId}").build(friend))
      .retrieve()
      .bodyToMono(FriendshipDto.class)
      .block();
  }
}
