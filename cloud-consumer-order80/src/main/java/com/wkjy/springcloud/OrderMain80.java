package com.wkjy.springcloud;


import com.netflix.discovery.EurekaNamespace;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import com.wkjy.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
//name表示是那个微服务名称要使用其他的规则 ，configuration 表示是更换的规则类
//@RibbonClient(name = "cloud-payment-service",configuration = MySelfRule.class)
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class, args);
//        RoundRobinRule
        //IRule
    }
}
