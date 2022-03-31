package com.talky.commons.client;

import com.talky.commons.auth.IAuthentication;
import lombok.AllArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@AllArgsConstructor
public abstract class AbstractTalkyClient {
  private IAuthentication authentication;
  private AbstractTalkyClientConfig config;

  public WebClient buildWebClient() {
    var wc = WebClient.builder()
      .baseUrl(config.getUri());
    if (authentication.getCurrentUserToken().isPresent()) {
      wc = wc.defaultHeader("Authorization", "Bearer %s".formatted(authentication.getCurrentUserToken().get()));
    }
    return wc.build();
  }
}
