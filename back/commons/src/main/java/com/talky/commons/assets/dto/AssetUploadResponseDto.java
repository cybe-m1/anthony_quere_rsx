package com.talky.commons.assets.dto;

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
  private String id;
  private URL url;
}
