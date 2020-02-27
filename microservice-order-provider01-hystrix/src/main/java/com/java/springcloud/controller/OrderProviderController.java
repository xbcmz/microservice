package com.java.springcloud.controller;

import com.java.springcloud.entity.TOrder;
import com.java.springcloud.service.OrderService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
* @Description: TODO
* @author: Mr.
* @create: 2020/2/27 15:36
**/
@RestController
@RequestMapping("/provider/order")
public class OrderProviderController {

    @Resource
    private OrderService orderService;
    @Resource
    private EurekaClient eurekaClient;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderProviderController.class);

    /**
     * HystrixCommond注解中的fallbackMethod指示的是：当该方法出异常时，调用processGetOrderHystrix方法
     * @param id id
     * @return 订单信息
     */
    @GetMapping("/get/{id}")
    @HystrixCommand(fallbackMethod = "processGetOrderHystrix")
    public TOrder getOrder(@PathVariable Long id) {
        TOrder order = orderService.findById(id);
        if (order == null) {
            throw new RuntimeException("数据库没有对应的信息");
        }
        return order;
    }

    /**
     * 上面getOrder()方法出异常后的熔断处理方法
     * @param id id
     * @return 订单信息
     */
    public TOrder processGetOrderHystrix(@PathVariable Long id) {
        return new TOrder().setId(id)
                .setName("未找到该ID的结果")
                .setPrice(0d)
                .setDbSource("No this datasource");
    }

    @GetMapping("/get/list")
    @HystrixCommand(fallbackMethod = "processGetOrderHystrix")
    public List<TOrder> getAll() {
        return orderService.findAll();
    }

    public List<TOrder> processGetOrderHystrix() {
        return new ArrayList<>();
    }

    /**
     * 暴露接口
     * @return
     */
    @GetMapping("/discovery")
    public Object discovery() {
        // 获取Eureka中所有的服务节点
        List<Application> applications = eurekaClient.getApplications().getRegisteredApplications();
        if (applications != null) {
            for (Application application : applications) {
                // 对外暴露的服务名称
                String name = application.getName();
                // 只看订单服务信息
                if ("MICROSERVICE-ORDER".equals(name)) {
                    // 服务有多少个实例，比如订单服务可能部署了多个，有多个订单服务注册到了eureka
                    List<InstanceInfo> instances = application.getInstances();
                    LOGGER.info("所有的订单服务：{}", instances);
                    if (instances != null) {
                        for (InstanceInfo info : instances) {
                            LOGGER.info("服务id：{}", info.getInstanceId());
                            LOGGER.info("服务主机：{}", info.getHostName());
                            LOGGER.info("服务端口：{}", info.getPort());
                        }
                    }
                    return instances;
                }
            }
        }
        return null;
    }

}