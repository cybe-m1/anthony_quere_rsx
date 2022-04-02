package com.talky.commons.assets;

import com.talky.commons.client.AbstractTalkyClientConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "network-config.assets-service")
class AssetsClientConfiguration extends AbstractTalkyClientConfig {
}
