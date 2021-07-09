package com.imooc.coupon.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * @Classname PeriodType
 * @Description 有效期的类型
 * @Date 2021/7/8 19:51
 * @Created by yemingjie
 */
@Getter
@AllArgsConstructor
public enum PeriodType {

    REGULAR("固定的（固定日期）", 1),
    SHIFT("变动的（以领取之日开始计算）", 2);

    /**
     * 有效期描述
     */
    private String description;
    private Integer code;

    public static PeriodType of(Integer code) {
        Objects.requireNonNull(code);

        return Stream.of(values())
                .filter(bean -> bean.getCode().equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(code + " not exists!"));
    }
}
