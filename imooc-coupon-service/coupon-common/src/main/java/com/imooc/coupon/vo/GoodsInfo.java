package com.imooc.coupon.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname GoodsInfo
 * @Description TODO
 * @Date 2021/7/19 20:05
 * @Created by yemingjie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsInfo {
    /**商品类型*/
    private Integer type;

    /**商品价格*/
    private Double price;

    /**商品数量*/
    private Integer count;

}
