package com.talky.commons.social;

import com.talky.commons.client.AbstractTalkyClientConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "network-config.social-service")
class SocialClientConfiguration extends AbstractTalkyClientConfig {
}
