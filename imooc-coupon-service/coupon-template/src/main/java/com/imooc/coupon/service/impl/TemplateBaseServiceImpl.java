package com.imooc.coupon.service.impl;

import com.imooc.coupon.dao.CouponTemplateDao;
import com.imooc.coupon.entity.CouponTemplate;
import com.imooc.coupon.exception.CouponException;
import com.imooc.coupon.service.ITemplateBaseService;
import com.imooc.coupon.vo.CouponTemplateSDK;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Classname TemplateBaseServiceImpl
 * @Description 优惠券模板基础服务接口实现
 * @Date 2021/7/14 19:54
 * @Created by yemingjie
 */
@Slf4j
@Service
public class TemplateBaseServiceImpl implements ITemplateBaseService {

    private final CouponTemplateDao templateDao;

    @Autowired
    public TemplateBaseServiceImpl(CouponTemplateDao templateDao) {
        this.templateDao = templateDao;
    }


    @Override
    public CouponTemplate buildTemplateInfo(Integer id) throws CouponException {
        Optional<CouponTemplate>  template = templateDao.findById(id);

        if (!template.isPresent()) {
            throw new CouponException("Template Is Not Exist :" + id);
        }
        return template.get();
    }

    /**
     * 查询可用
     * @return
     */
    @Override
    public List<CouponTemplateSDK> findAllUsableTemplate() {
        List<CouponTemplate> templates =
                templateDao.findAllByAvailableAAndExpired(true, false);
        return templates.stream()
                .map(this::template2TemplateSDK).collect(Collectors.toList());
    }

    @Override
    public Map<Integer, CouponTemplateSDK> findIds2TemplateSDK(Collection<Integer> ids) {

        List<CouponTemplate> templates = templateDao.findAllById(ids);

        return templates.stream().map(this::template2TemplateSDK).collect(Collectors.toMap(
                CouponTemplateSDK::getId, Function.identity()
        ));
    }

    /**
     * 将CouponTemplate 转换为 CouponTemplateSDK
     * @param template
     * @return
     */
    private CouponTemplateSDK template2TemplateSDK(CouponTemplate template) {
        return new CouponTemplateSDK(
            template.getId(),
            template.getName(),
            template.getLogo(),
            template.getDesc(),
            template.getCategory().getCode(),
            template.getProductLine().getCode(),
            template.getKey(), // 并不是拼装好的Template Key
            template.getTarget().getCode(),
            template.getRule()
        );
    }
}
