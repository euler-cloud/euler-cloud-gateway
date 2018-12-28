package net.eulerframework.cloud.gateway;

import org.eulerframework.cloud.security.filter.AuthenticationZuulFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringBootApplication
public class EulerCloudGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(EulerCloudGatewayApplication.class, args);
    }

    @Bean
    public AuthenticationZuulFilter authenticationZuulFilter() {
        return new AuthenticationZuulFilter();
    }
}
