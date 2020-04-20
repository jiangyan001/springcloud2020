package com.wkjy.springcloud.controller;

import com.wkjy.springcloud.entity.CommonResult;
import com.wkjy.springcloud.entity.Payment;
import com.wkjy.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

@RestController// Controller
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService service;
    @Value("${server.port}")
    private String serverPort;
    @Resource
    /**
     * 服务发现，获取该服务的信息
     */
    private DiscoveryClient discoveryClient;


    @RequestMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        System.out.println(payment);
        //插入成功返回主键，插入失败，返回小于0
        int result = service.create(payment);
        System.out.println("结果："+result);
        log.info("***************插入结果："+result);

        if(result>0){
            return new CommonResult(200,"插入成功!ServerPort:"+serverPort,result);
        }else{
            return new CommonResult(444,"插入数据库失败！");
        }
    }
    @RequestMapping(value = "/payment/update")
    public CommonResult update(@RequestBody Payment payment){
        int result = service.update(payment);
        if(result>0){
            return new CommonResult(200,"更新成功!ServerPort:"+serverPort,result);
        }else{
            return new CommonResult(444,"更新数据库失败！");
        }
    }

    @RequestMapping(value = "/payment/del")
    public CommonResult del(@RequestBody Long id){
        LinkedList list = null;
        Stack stack = null;
        int result = service.del(id);
        if(result>0){
            return new CommonResult(200,"删除成功!ServerPort:"+serverPort,result);
        }else{
            return new CommonResult(444,"删除数据库失败！");
        }
    }
    @RequestMapping(value = "/payment/selectAll")
    public CommonResult selectAll(){
        return new CommonResult(200,"查询成功!ServerPort:"+serverPort,service.selectAll());

        //ResponseEntity entity =
    }

    @RequestMapping(value = "/payment/discovery")
    public Object getDiscovery(){
        //获取服务列表清单   (获取所有微服务名称)
        List<String> services = discoveryClient.getServices();
        for(String item : services){

            log.info("****** item:"+item);
        }


        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance instance : instances) {
            log.info("********** instances:"+instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
//        System.out.println(instances.get(0));
//        instance.getServiceId();
//        instance.getHost();//主机名称
//        instance.getInstanceId();
//        instance.getMetadata();
//        instance.getPort();//端口号
//        instance.getScheme();
//        instance.getUri();
//        instance.getClass();
        return this.discoveryClient;
    }


    @RequestMapping(value = "/payment/lb")
    public String getServerPort(){
        return serverPort;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try{
            //让程序暂停三秒钟，模拟复杂业余所需要运行的时间
            //OpenFeign默认等待一秒钟，超过后报错
            //为了解决这个问题，需要设置Robbin开启OpenFeign的客户端超市控制
            TimeUnit.SECONDS.sleep(3);
        }catch (Exception e){
            e.printStackTrace();
        }
        return serverPort;
    }
}