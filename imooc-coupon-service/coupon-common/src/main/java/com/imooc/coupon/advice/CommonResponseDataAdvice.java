package com.imooc.coupon.advice;

import com.imooc.coupon.annotation.IgnoreResonseAdvice;
import com.imooc.coupon.vo.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Classname CommonResponseDataAdvice
 * @Description 统一响应
 * @Date 2021/7/6 20:10
 * @Created by yemingjie
 */
@RestControllerAdvice
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object> {

    /**
     * 判断是否需要对响应进行处理
     * @param methodParameter
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        // 如果当前方法所在的类标识了@IngoreResponseAdvice注解，则不需要处理
        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResonseAdvice.class)) {
            return false;
        }

        // 如果当前方法标识了@IngoreResponseAdvice注解，则不需要处理
        if (methodParameter.getMethod().isAnnotationPresent(IgnoreResonseAdvice.class)) {
            return false;
        }
        // 对响应进行处理，执行beforeBodyWrite方法
        return true;
    }

    /**
     * 返回前的处理
     * @param o
     * @param methodParameter
     * @param mediaType
     * @param aClass
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        // 定义最终的返回对象
        CommonResponse<Object> response = new CommonResponse<>(0, "");

        // 如果o是null，response不需要设置data
        if (null == o) {
            return response;
        } else if (o instanceof CommonResponse){
            // 如果o已经是CommonResponse类型，则不需要处理
            response = (CommonResponse<Object>) o;
        } else {
            // 否则把响应对象作为CommonResponse的data部分
            response.setData(o);
        }
        return null;
    }
}
