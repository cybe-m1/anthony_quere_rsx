package com.talky.commons.users;

import com.talky.commons.client.AbstractTalkyClientConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "network-config.user-service")
class UsersClientConfiguration extends AbstractTalkyClientConfig {
}
