package com.imooc.coupon.constant;

/**
 * @Classname Constant
 * @Description TODO
 * @Date 2021/7/13 19:46
 * @Created by yemingjie
 */
public class Constant {

    /** kafka消息的TOPIC */
    public static final String TOPIC = "imooc_user_coupon_op";

    /**
     * Redis Key 前缀
     */
    public static class RedisPrefix {
        /** 优惠券码key 前缀 */
        public static final String COUPON_TEMPLATE = "imooc_coupon_template_code_";

        /** 用户当前所有可用的优惠券前缀 */
        public static final String USER_COUPON_USABLE = "imooc_user_coupon_usable_";

        /** 用户当前所有已使用的优惠券 key 前缀 */
        public static final String USER_COUPON_USED = "imooc_user_coupon_used_";

        /** 用户当前所有已过期的优惠券 key 前缀 */
        public static final String USER_COUPON_EXPIRED = "imooc_user_coupon_expired_";
    }
}
