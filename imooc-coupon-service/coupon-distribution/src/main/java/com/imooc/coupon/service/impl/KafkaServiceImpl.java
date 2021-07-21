package com.imooc.coupon.service.impl;

import com.alibaba.fastjson.JSON;
import com.imooc.coupon.constant.Constant;
import com.imooc.coupon.constant.CouponStatus;
import com.imooc.coupon.service.IKafkaService;
import com.imooc.coupon.vo.CouponKafkaMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Classname KafkaServiceImpl
 * @Description kafka 相关服务接口实现
 * 核心思想：
 * 将Cache 中的Coupon状态变化同步到DB中, 通过kafka异步同步到DB中
 * @Date 2021/7/21 19:39
 * @Created by yemingjie
 */
@Slf4j
@Component
public class KafkaServiceImpl implements IKafkaService {


    @Override
    @KafkaListener(topics = {Constant.TOPIC}, groupId = "imooc-coupon-1")
    public void consumeCouponKafkaMessage(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            CouponKafkaMessage couponInfo = JSON.parseObject(message.toString(), CouponKafkaMessage.class);
            log.info("Receive CouponKafkaMessage: {}", message.toString());
            CouponStatus status = CouponStatus.of(couponInfo.getStatus());
            switch (status) {
                // 先有id才能异步添加，所以USABLE不需要添加
                case USABLE:
                    break;
                case USED:
                    break;
                case EXPIRED:
                    break;
            }
        }


    }
}
