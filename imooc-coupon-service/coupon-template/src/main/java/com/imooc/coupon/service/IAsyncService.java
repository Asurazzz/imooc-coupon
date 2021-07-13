package com.imooc.coupon.service;

import com.imooc.coupon.entity.CouponTemplate;

/**
 * @Classname IAsyncService
 * @Description 异步服务接口定义
 * @Date 2021/7/12 20:10
 * @Created by yemingjie
 */
public interface IAsyncService {
    /**
     * <h2>根据模板异步的创建优惠券码</h2>
     * @param template {@link CouponTemplate} 优惠券模板实体
     */
    void asyncConstructCouponByTemplate(CouponTemplate template);
}
