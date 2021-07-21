package com.imooc.coupon.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Classname CouponKafkaMessage
 * @Description 优惠券kafka消息对象定义
 * @Date 2021/7/21 19:46
 * @Created by yemingjie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponKafkaMessage {
    /**优惠券状态*/
    private Integer status;

    /**Coupon主键*/
    private List<Integer> ids;
}
