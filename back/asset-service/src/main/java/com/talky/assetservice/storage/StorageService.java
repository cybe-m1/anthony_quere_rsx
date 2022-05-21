package com.talky.assetservice.storage;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.HttpMethod;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
class StorageService implements IStorage {
  private final Storage storage;
  private final StorageConfiguration storageConfiguration;

  private String generateKey(String extention) {
    return "%s.%s".formatted(UUID.randomUUID(), extention);
  }

  private BlobInfo buildBlobInfo(String group, String key) {
    return BlobInfo.newBuilder(storageConfiguration.getBucketName(), "%s/%s".formatted(group, key)).build();
  }

  public URL getUploadTemporaryLink(String group, String extention) {
    var key = generateKey(extention);

    Map<String, String> extensionHeaders = new HashMap<>();
    extensionHeaders.put("Content-Type", "application/octet-stream");

    return storage.signUrl(
      buildBlobInfo(group, key),
      30,
      TimeUnit.MINUTES,
      Storage.SignUrlOption.httpMethod(HttpMethod.PUT),
      Storage.SignUrlOption.withExtHeaders(extensionHeaders),
      Storage.SignUrlOption.withV4Signature()
    );
  }

  public String uploadFile(String group, byte[] fileContent, String extention) {
    var key = generateKey(extention);

    storage.create(
      buildBlobInfo(group, key),
      fileContent
    );

    return key;
  }


  public URL getTemporaryLink(String group, String identifier) {
    return storage.signUrl(buildBlobInfo(group, identifier), 12, TimeUnit.HOURS);
  }


}
