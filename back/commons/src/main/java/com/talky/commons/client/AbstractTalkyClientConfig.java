package com.talky.commons.client;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractTalkyClientConfig {
  private String uri;
  private String port;
}
