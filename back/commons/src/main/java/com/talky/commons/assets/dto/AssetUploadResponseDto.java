package com.talky.commons.assets.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.URL;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssetUploadResponseDto {
  @Schema(description = "Id of the asset, assets ids or composed of a UUID followed by the file extention")
  private String id;
  @Schema(description = "Temporary link of the asset")
  private URL url;
}
