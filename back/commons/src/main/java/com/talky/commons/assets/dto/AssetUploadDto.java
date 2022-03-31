package com.talky.commons.assets.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class AssetUploadDto {
  private MultipartFile file;
  private String group;
}
