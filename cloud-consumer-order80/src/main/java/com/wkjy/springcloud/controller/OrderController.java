package com.wkjy.springcloud.controller;


import com.netflix.loadbalancer.RoundRobinRule;
import com.wkjy.springcloud.entity.CommonResult;
import com.wkjy.springcloud.entity.Payment;
import com.wkjy.springcloud.lb.LoadBalancer;
import com.wkjy.springcloud.lb.MyLb;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@Slf4j
public class OrderController {


    //private final String PAYMENT_URL = "http://localhost:8001";
    private final String PAYMENT_URL = "http://cloud-payment-service";

    @Autowired
    private LoadBalancer lb;
    @Autowired
    public DiscoveryClient discoveryClient;

    @Resource
    private RestTemplate restTemplate;
    @RequestMapping(value = "/consumer/payment/create", method = RequestMethod.POST)
    public CommonResult<Payment> create(@RequestBody Payment payment){
        log.info("客户端发送创建请求");
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create", payment, CommonResult.class);
    }



    @RequestMapping(value = "/consumer/payment/update")
    public CommonResult update(@RequestBody Payment payment){
        log.info("客户端发送更新请求");
        return restTemplate.postForObject(PAYMENT_URL+"/payment/update", payment, CommonResult.class);
    }

    @RequestMapping(value = "/consumer/payment/del")
    public CommonResult del(Long id){
        log.info("客户端发送删除请求");
       return restTemplate.postForObject(PAYMENT_URL+"/payment/del",id, CommonResult.class);
    }
    private AtomicInteger nextServerCyclicCounter = new AtomicInteger(0);
    private int ccc = 2;
    private int incrementAndGetModulo(int modulo) {
        for (;;) {
            int current = nextServerCyclicCounter.get();
            int next = (current + 1) % modulo;
            if (nextServerCyclicCounter.compareAndSet(current, next))
                return next;
        }
    }
    @RequestMapping(value = "/consumer/payment/selectAll")
    public CommonResult selectAll(){
        log.info("客户端发送查找请求");
        int bbb = incrementAndGetModulo(ccc);
        System.out.println("bbb的值:"+bbb);
        System.out.println("ccc的值:"+ccc);
        return restTemplate.getForObject(PAYMENT_URL+"/payment/selectAll",CommonResult.class);


//        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL+"/payment/selectAll",CommonResult.class);
//
//        if(entity.getStatusCode().is2xxSuccessful()){//执行成功
//            return entity.getBody();
//        }else{
//            return new CommonResult(1,"错误！");
//        }
    }

    @RequestMapping(value = "consumer/payment/lb")
    public String getPaymentLb(){
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        if(instances == null || instances.size() == 0){
            return "没有此健康的服务";
        }
        ServiceInstance serviceInstance = lb.instance(instances);
        URI uri = serviceInstance.getUri();
        log.info(uri.toString());
        return restTemplate.getForObject(uri+"/payment/lb", String.class);
    }
}
