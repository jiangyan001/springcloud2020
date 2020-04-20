package com.wkjy.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer {

    //获取到Eureka健康的机器总数
    ServiceInstance instance(List<ServiceInstance> serviceInstances);
}
