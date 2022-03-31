package com.talky.assetservice.assets;


import com.talky.commons.assets.dto.AssetTemporaryLinkResponseDto;
import com.talky.commons.assets.dto.AssetUploadDto;
import com.talky.commons.assets.dto.AssetUploadResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/assets")
class AssetsController {
  private final AssetsService assetsService;

  @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
  public AssetUploadResponseDto uploadAsset(@ModelAttribute AssetUploadDto dto) {
    return assetsService.upload(dto.getGroup(), dto.getFile());
  }

  @GetMapping("/{groupId}/{assetId}")
  public AssetTemporaryLinkResponseDto getAssetLink(@PathVariable String groupId, @PathVariable String assetId) {
    return assetsService.getImageTemporaryLink(groupId, assetId);
  }
}
