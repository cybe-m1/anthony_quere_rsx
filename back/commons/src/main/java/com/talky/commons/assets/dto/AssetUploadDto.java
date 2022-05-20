package com.talky.commons.assets.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class AssetUploadDto {

  @Schema(description = "File to Upload")
  private MultipartFile file;
  @Schema(description = "Asset group where the asset should be uploaded")
  private String group;
}
