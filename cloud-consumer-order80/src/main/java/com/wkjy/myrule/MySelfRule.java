package com.wkjy.myrule;


import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//自定义均衡规则
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule(){
        //随机
        //RoundRobinRule
        return new RandomRule();
    }
}
