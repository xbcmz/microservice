package com.java.springcloud.config;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
* @Description: TODO 配置RestTemplate
* @author: Mr.
* @create: 2020/2/27 16:07
**/
@Configuration
public class RestTemplateConfig {

    /**
     * '@LoadBalanced'注解表示使用Ribbon实现客户端负载均衡
     * @return RestTemplate
     */
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    /**
     * 指定其他负载均衡策略 注：使用自定义负载均衡时，该方法需要去掉
     * @return IRule
     */
//    @Bean
//    public IRule myRule() {
//        // 指定策略：随机
//        return new RetryRule();
//    }
}
