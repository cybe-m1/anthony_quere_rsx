package com.talky.commons.assets.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssetTemporaryLinkResponseDto {
  @Schema(description = "Temporary Url of an asset. Warning : The Asset miht not exist.")
  private URL url;
}
