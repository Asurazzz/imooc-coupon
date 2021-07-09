package com.imooc.coupon.converter;

import com.imooc.coupon.constant.CouponCategory;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

/**
 * @Classname CouponCategoryConverter
 * @Description 优惠券分类枚举属性转换器
 * AttributeConverter<X, Y>
 *     x:实体属性的类型
 *     Y：数据库字段的类型
 * @Date 2021/7/9 19:51
 * @Created by yemingjie
 */
@Convert
public class CouponCategoryConverter implements AttributeConverter<CouponCategory, String> {


    /**
     * 将实体属性X转换为Y存储到数据库中，插入和更新执行的操作
     * @param couponCategory
     * @return
     */
    @Override
    public String convertToDatabaseColumn(CouponCategory couponCategory) {
        return couponCategory.getCode();
    }

    /**
     * 将数据库的字段Y转换为实体属性X，查询操作时执行
     * @param s
     * @return
     */
    @Override
    public CouponCategory convertToEntityAttribute(String code) {
        return CouponCategory.of(code);
    }
}
