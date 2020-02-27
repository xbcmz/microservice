package com.java.springcloud.service;


import com.java.springcloud.entity.TOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
* @Description: TODO feign客户端
* @author: Mr.
* @create: 2020/2/27 16:57
**/
//@FeignClient(value = "MICROSERVICE-ORDER")
@FeignClient(value = "MICROSERVICE-ORDER", fallbackFactory = OrderClientServiceFallbackFactory.class)
public interface OrderClientService {

    @GetMapping("/provider/order/get/{id}")
    TOrder getOrder(@PathVariable(value = "id") Long id);

    @GetMapping("/provider/order/get/list")
    List<TOrder> getAll();
}
