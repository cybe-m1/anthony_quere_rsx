package com.talky.assetservice.storage;

import java.net.URL;

public interface IStorage {
  String uploadFile(String group, byte[] fileContent, String fileExtention);

  URL getTemporaryLink(String group, String identifier);
}
