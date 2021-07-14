package com.imooc.coupon.schedule;

import com.imooc.coupon.dao.CouponTemplateDao;
import com.imooc.coupon.entity.CouponTemplate;
import com.imooc.coupon.vo.TemplateRule;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Classname ScheduledTask
 * @Description 定时清理已过期的优惠券模板
 * @Date 2021/7/14 20:16
 * @Created by yemingjie
 */
@Slf4j
@Component
public class ScheduledTask {


    private final CouponTemplateDao templateDao;
    @Autowired
    public ScheduledTask(CouponTemplateDao templateDao) {
        this.templateDao = templateDao;
    }

    /**
     * 下线已过期的规则模板
     */
    @Scheduled(fixedRate = 60 * 60 * 1000)
    public void offlineCouponTemplate() {
        log.info("Start To Expire CouponTemplate");

        List<CouponTemplate> templates = templateDao.findAllByExpired(false);
        if (CollectionUtils.isEmpty(templates)) {
            log.info("Done To Expire CouponTemplate!");
            return;
        }

        Date cur = new Date();
        List<CouponTemplate> expiredTemplates = new ArrayList<>(templates.size());
        templates.forEach(t -> {
            // 根据优惠券模板过期规则来校验
            TemplateRule rule = t.getRule();
            if (rule.getExpiration().getDeadline() < cur.getTime()) {
                // 比当前时间小，过期状态
                t.setExpired(true);
                expiredTemplates.add(t);
            }
        });

        if (CollectionUtils.isNotEmpty(expiredTemplates)) {
            log.info("Expired CouponTemplate Num : {}" + templateDao.saveAll(expiredTemplates));
        }

        log.info("Done To Expire CouponTemplate!");
    }
}
