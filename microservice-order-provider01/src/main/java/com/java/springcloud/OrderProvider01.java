package com.java.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 订单服务
 * "@EnableEurekaClient" 服务启动后会自动注册到eureka服务中心
 * "@EnableCircuitBreaker" 启动Hystrix
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.java.springcloud.dao")
@EnableCircuitBreaker
public class OrderProvider01 {
    public static void main(String[] args) {
        SpringApplication.run(OrderProvider01.class, args);
    }
}