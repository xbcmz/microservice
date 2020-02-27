package com.java.springcloud.controller;

import com.java.springcloud.entity.TOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
* @Description: TODO 订单的消费者服务
* @author: Mr.
* @create: 2020/2/27 16:09
**/
@RestController
@RequestMapping("/consumer/order")
public class OrderConsumerController {

    /**
     * 订单服务提供者模块的 url 前缀
     */
    private static final String ORDER_PROVIDER_URL_PREFIX = "http://MICROSERVICE-ORDER";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/get/{id}")
    public TOrder getOrder(@PathVariable Long id) {
        return restTemplate.getForObject(ORDER_PROVIDER_URL_PREFIX + "/provider/order/get/" + id, TOrder.class);
    }

    @SuppressWarnings("unchecked")
    @GetMapping("/get/list")
    public List<TOrder> getAll() {
        return restTemplate.getForObject(ORDER_PROVIDER_URL_PREFIX + "/provider/order/get/list", List.class);
    }

    @GetMapping("/discovery")
    public Object discovery() {
        return restTemplate.getForObject(ORDER_PROVIDER_URL_PREFIX + "/provider/order/discovery", Object.class);
    }
}
