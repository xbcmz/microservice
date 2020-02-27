package com.java.springcloud;

import com.java.myrule.MyRuleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "MICROSERVICE-ORDER", configuration = MyRuleConfig.class)
public class OrderConsumer {

    public static void main(String[] args) {
        SpringApplication.run(OrderConsumer.class, args);
    }
}
