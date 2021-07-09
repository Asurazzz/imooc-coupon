package com.imooc.coupon.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * @Classname ProductLine
 * @Description 产品线枚举
 * @Date 2021/7/8 19:40
 * @Created by yemingjie
 */
@Getter
@AllArgsConstructor
public enum ProductLine {
    DAMAO("大猫", 1),
    DABAO("大宝", 2);


    /**
     * 产品线描述
     */
    private String description;

    /**
     * 编码编码
     */
    private Integer code;

    public static ProductLine of(Integer code) {
        Objects.requireNonNull(code);

        return Stream.of(values())
                .filter(bean -> bean.getCode().equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(code + " not exists!"));
    }

}
