package com.imooc.coupon.dao;

import com.imooc.coupon.entity.CouponTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Classname CouponTemplateDao
 * @Description CouponTemplateDao接口定义
 * @Date 2021/7/12 19:32
 * @Created by yemingjie
 */
public interface CouponTemplateDao extends JpaRepository<CouponTemplate, Integer> {

    /**
     * 根据模板名称查询模板
     * @param name
     * @return
     */
    CouponTemplate findByName(String name);

    /**
     * 根据available 和 expired查看模板记录
     * @param available
     * @param expired
     * @return
     */
    List<CouponTemplate> findAllByAvailableAndExpired(Boolean available, Boolean expired);

    /**
     * 根据expired标记模板记录
     * @param expired
     * @return
     */
    List<CouponTemplate> findAllByExpired(Boolean expired);
}

