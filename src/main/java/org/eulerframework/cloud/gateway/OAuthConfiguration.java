/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.eulerframework.cloud.gateway;

import org.eulerframework.boot.autoconfigure.support.security.EulerBootSecurityProperties;
import org.eulerframework.cloud.security.filter.AuthenticationZuulFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import java.util.Arrays;

@Configuration
@EnableResourceServer
public class OAuthConfiguration extends ResourceServerConfigurerAdapter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EulerBootSecurityProperties eulerBootSecurityProperties;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        String[] ignoredPatterns = this.eulerBootSecurityProperties.getIgnoredPatterns();
        if(ignoredPatterns != null && ignoredPatterns.length > 0) {
            if(this.logger.isInfoEnabled()) {
                this.logger.info("config resource server ignored patterns: {}", Arrays.toString(ignoredPatterns));
            }

            http.authorizeRequests()
                    .antMatchers(ignoredPatterns).permitAll()
                    .anyRequest().authenticated();
        } else {
            http.authorizeRequests().anyRequest().authenticated();
        }
    }

    @Bean
    public AuthenticationZuulFilter authenticationZuulFilter() {
        return new AuthenticationZuulFilter(this.eulerBootSecurityProperties.getIgnoredPatterns());
    }
}
