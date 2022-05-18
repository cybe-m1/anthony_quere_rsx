package com.talky.assetservice.assets;

import com.talky.assetservice.storage.IStorage;
import com.talky.commons.assets.dto.AssetTemporaryLinkResponseDto;
import com.talky.commons.assets.dto.AssetUploadResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class AssetsService {
  private final IStorage storage;

  AssetUploadResponseDto upload(String group, MultipartFile file) {

    var originalFileExtention = Optional.ofNullable(file.getOriginalFilename())
      .map(filename -> {
        var splitted = filename.split("\\.");
        if (splitted.length > 1) {
          return splitted[splitted.length - 1];
        }
        return "";
      })
      .orElse("");

    try {
      var key = storage.uploadFile(group, file.getBytes(), originalFileExtention);
      var uri = storage.getTemporaryLink(group, key);
      return new AssetUploadResponseDto(key, uri);
    } catch (IOException ex) {
      return null;
    }
  }

  AssetTemporaryLinkResponseDto getImageTemporaryLink(String groupId, String assetId) {
    return new AssetTemporaryLinkResponseDto(storage.getTemporaryLink(groupId, assetId));
  }

}
