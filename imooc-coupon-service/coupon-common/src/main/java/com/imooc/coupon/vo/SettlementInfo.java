package com.imooc.coupon.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Classname SettlementInfo
 * @Description 结算信息对象定义
 * 包含： 1.userId
 *       2.商品信息（列表）
 *       3.优惠券列表
 *       4.结算结果金额
 * @Date 2021/7/19 20:06
 * @Created by yemingjie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SettlementInfo {
    /**用户id*/
    private Long userId;

    /**商品信息*/
    private List<GoodsInfo> goodsInfos;

    /**结算金额*/
    private Double cost;

    /**是否使结算生效，即核销*/
    private Boolean employ;

    /**优惠券列表*/
    private List<CouponTemplateInfo> couponTemplateInfos;

    /**
     * 优惠券和模板信息
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CouponTemplateInfo {
        /**Coupon 主键*/
        private Integer id;
        /**优惠券对应的模板对象*/
        private CouponTemplateSDK template;
    }
}
