package com.java.springcloud.controller;


import com.java.springcloud.service.OrderClientService;
import com.java.springcloud.entity.TOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/consumer/order")
public class OrderConsumerController {

    /**
     * 定义在microservice-common模块的feinClient
     */
    @Resource
    private OrderClientService orderClientService;

    @GetMapping("/get/{id}")
    public TOrder getOrder(@PathVariable Long id) {
        return orderClientService.getOrder(id);
    }

    @GetMapping("/get/list")
    public List<TOrder> getAll() {
        return orderClientService.getAll();
    }
}
