package com.talky.commons.assets;

import com.talky.commons.assets.dto.AssetTemporaryLinkResponseDto;
import com.talky.commons.auth.IAuthentication;
import com.talky.commons.client.AbstractTalkyClient;
import org.springframework.stereotype.Component;

@Component
class AssetsClient extends AbstractTalkyClient implements IAssets {
  public AssetsClient(IAuthentication authentication, AssetsClientConfiguration config) {
    super(authentication, config);
  }

  public AssetTemporaryLinkResponseDto getTemporaryLink(String groupId, String assetId) {
    return buildWebClient()
      .get()
      .uri(uriBuilder ->
        uriBuilder
          .path("/api/v1/assets/{groupId}/{assetId}")
          .build(groupId, assetId)
      )
      .retrieve()
      .bodyToMono(AssetTemporaryLinkResponseDto.class)
      .block();
  }


}
