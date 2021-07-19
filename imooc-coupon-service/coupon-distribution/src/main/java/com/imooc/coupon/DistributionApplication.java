package com.imooc.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.client.RestTemplate;

/**
 * @Classname DistributionApplication
 * @Description 分发微服务启动入口
 * @Date 2021/7/19 19:29
 * @Created by yemingjie
 */
@EnableJpaAuditing   // 审计
@EnableCircuitBreaker // 熔断
@EnableFeignClients  // 启用feign
@SpringBootApplication
@EnableDiscoveryClient
public class DistributionApplication {

    @Bean
    @LoadBalanced  //负载均衡注解
    RestTemplate restTemplate() {
        return new RestTemplate();
    }


    public static void main(String[] args) {
        SpringApplication.run(DistributionApplication.class, args);
    }
}
