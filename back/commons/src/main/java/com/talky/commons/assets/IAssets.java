package com.talky.commons.assets;

import com.talky.commons.assets.dto.AssetTemporaryLinkResponseDto;

public interface IAssets {
  AssetTemporaryLinkResponseDto getTemporaryLink(String groupId, String assetId);
}
