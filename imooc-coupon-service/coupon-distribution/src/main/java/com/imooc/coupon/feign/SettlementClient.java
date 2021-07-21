package com.imooc.coupon.feign;

import com.imooc.coupon.exception.CouponException;
import com.imooc.coupon.vo.CommonResponse;
import com.imooc.coupon.vo.SettlementInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author admin
 * @Classname SettlementClient
 * @Description 优惠券结算微服务Feign接口定义
 * @Date 2021/7/21 20:20
 * @Created by yemingjie
 */
@FeignClient(value = "eureka-client-coupon-settlement")
public interface SettlementClient {

    /**
     * <h2>优惠券规则计算</h2>
     * */
    @RequestMapping(value = "/coupon-settlement/settlement/compute",
            method = RequestMethod.POST)
    CommonResponse<SettlementInfo> computeRule(
            @RequestBody SettlementInfo settlement) throws CouponException;
}
