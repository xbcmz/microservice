package com.java.springcloud.service;


import com.java.springcloud.entity.TOrder;
import java.util.List;

public interface OrderService {

    TOrder findById(Long id);

    List<TOrder> findAll();
}
