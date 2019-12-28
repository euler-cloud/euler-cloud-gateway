package net.eulerframework.cloud.gateway.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
@ConfigurationProperties(prefix = "euler.gateway.oauth2.resource")
public class EulerGatewayOAuth2ResourceServerProperties {
    private Set<String> ignoredPatterns;

    public Set<String> getIgnoredPatterns() {
        return ignoredPatterns;
    }

    public void setIgnoredPatterns(Set<String> ignoredPatterns) {
        this.ignoredPatterns = ignoredPatterns;
    }
}
