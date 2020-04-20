package com.wkjy.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ Description   :  支付模块主启动类
 * @ Author        :  JY
 * @ CreateDate    :  2020/3/14 1:10
 * @ UpdateUser    :  JY
 * @ UpdateDate    :  2020/3/14 1:10
 * @ UpdateRemark  :  修改内容
 * @ Version       :  1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
public class PaymentMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8001.class, args);
    }
}
