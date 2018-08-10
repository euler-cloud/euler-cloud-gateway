package net.eulerframework.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class EulerCloudGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(EulerCloudGatewayApplication.class, args);
    }
}
