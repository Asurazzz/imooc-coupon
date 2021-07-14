package com.imooc.coupon.service.impl;

import com.imooc.coupon.dao.CouponTemplateDao;
import com.imooc.coupon.entity.CouponTemplate;
import com.imooc.coupon.exception.CouponException;
import com.imooc.coupon.service.IAsyncService;
import com.imooc.coupon.service.IBuildTemplateService;
import com.imooc.coupon.vo.TemplateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Classname BuildTemplateServiceImpl
 * @Description TODO
 * @Date 2021/7/14 19:41
 * @Created by yemingjie
 */
@Slf4j
@Service
public class BuildTemplateServiceImpl implements IBuildTemplateService {

    /** 异步服务*/
    private final IAsyncService asyncService;
    private final CouponTemplateDao templateDao;

    @Autowired
    public BuildTemplateServiceImpl(IAsyncService asyncService, CouponTemplateDao templateDao) {
        this.asyncService = asyncService;
        this.templateDao = templateDao;
    }

    @Override
    public CouponTemplate buildTemplate(TemplateRequest request) throws CouponException {
        if (!request.validate()) {
            throw new CouponException("BuildTemplate Param Is Not Vaild!");
        }
        if (null != templateDao.findByName(request.getName())) {
            throw new CouponException("Exist Same Name Template!");
        }

        // 构造CouponTemlate 并保存到数据库中
        CouponTemplate couponTemplate = requestToTemplate(request);
        // 返回有id的优惠券
        couponTemplate = templateDao.save(couponTemplate);

        // 根据优惠券模板异步生成优惠券码
        asyncService.asyncConstructCouponByTemplate(couponTemplate);

        return couponTemplate;
    }


    private CouponTemplate requestToTemplate(TemplateRequest request) {
        return new CouponTemplate(
                request.getName(),
                request.getLogo(),
                request.getDesc(),
                request.getCategory(),
                request.getProductLine(),
                request.getCount(),
                request.getUserId(),
                request.getTarget(),
                request.getRule()
        );

    }
}
