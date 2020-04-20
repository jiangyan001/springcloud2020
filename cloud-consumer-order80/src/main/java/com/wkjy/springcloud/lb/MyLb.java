package com.wkjy.springcloud.lb;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLb implements LoadBalancer {
    //原子整形类，初始值设置为0
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    //CAS原理，他是传入两个值，一个期望值，一个更新值，执行CAS操作室，会先对内存中的值和期望值进行比对，如果相同就进行更新，不同就不更新


    //负载均衡的轮询算法  CAS+自旋锁
    public final int getIndex(int listSize){
        for(;;){
            int current = this.atomicInteger.get();
            int next = (current+1) % listSize;
            if(atomicInteger.compareAndSet(current, next)){
                return next;
            }
        }
    }

    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
//        IRule
        int index = getIndex(serviceInstances.size());
        return serviceInstances.get(index);
    }
}
