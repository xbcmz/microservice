package com.java.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class TOrder {

    private Long id; // 主键id
    private String name; // 商品名称
    private Double price; // 商品价格
    private String dbSource; // 所存的数据库
}