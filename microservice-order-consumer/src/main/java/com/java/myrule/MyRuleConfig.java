/*
 *
 * Copyright (C) 1999-2014 IFLYTEK Inc.All Rights Reserved.
 *
 * FileName：springcloud_study
 *
 * Description：
 *
 * History：
 * Version   Author      Date            Operation
 * 1.0	     admin    2019/1/2116:18	  Create
 */
package com.java.myrule;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
* @Description: TODO Ribbon自定义配置
* @author: Mr.
* @create: 2020/2/27 16:44
**/
@Configuration
public class MyRuleConfig {
    @Bean
    public IRule myselfRule() {
        // 指定策略：我们自定义的策略
        return new CustomRule();
    }
}
