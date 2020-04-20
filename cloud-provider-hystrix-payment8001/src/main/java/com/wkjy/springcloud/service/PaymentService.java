package com.wkjy.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
@Service
public class PaymentService {

    public String  paymentInfo_Ok(Integer id){
        return "线程池：  "+Thread.currentThread().getName()+"   paymentInfo_Ok,id:"+id+"\tO(∩_∩)O哈哈~";
    }


    /**
     *     服务降级与服务熔断的区别
     *
     */

//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",
//            commandProperties = { @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "5000")})
            //代表的是 execution.isolation.thread.timeoutInMilliseconds 的超时时间为3秒 超过3秒就调用fallback
    public String  paymentInfo_TimeOut(Integer id){
        //异常1：超时
        int timeNUmber = 3000;
        try {
            //TimeUnit.SECONDS.sleep(timeNUmber);
            TimeUnit.MILLISECONDS.sleep(timeNUmber);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "线程池：  "+Thread.currentThread().getName()+"   paymentInfo_TimeOut,id:"+id+"\tO(∩_∩)O哈哈~    耗时:"+timeNUmber+"毫秒";
        //1155+800


        //异常2：程序出错
//        int timeNUmber = 10/0;
//        return "线程池：  "+Thread.currentThread().getName()+"   paymentInfo_TimeOut,id:"+id+"\tO(∩_∩)O哈哈~    耗时:"+timeNUmber+"秒";
    }

    /**
     * 服务处理异常的备选方案
     * fallbcak的处理，方法头保持一致
     */
    public String  paymentInfo_TimeOutHandler(Integer id){
        return "线程池：  "+Thread.currentThread().getName()+"   paymentInfo_TimeOutHandler,id:"+id+"\to(╥﹏╥)o";

    }


//    //--------服务熔断
//    @HystrixCommand(defaultFallback = "paymentCircuitBreaker_fallback",commandProperties = {
//            //意思是10秒内，发送10次请求，失败率达到60%就短路
//            @HystrixProperty(name="circuitBreaker.enabled",value = "true"),//是否开启断路器
//            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
//            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期 or 时间范围
//            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "60")//失败率达到多少后跳闸
//    })
//    public String paymentCircuitBreaker(Integer id){
//        if(id<0){
//            throw new RuntimeException("ID不能为负数！");
//        }
//        String serialNumber = IdUtil.simpleUUID();
//        return Thread.currentThread().getName()+"\t调用成功！流水号："+serialNumber;
//    }
//
//    //服务熔断的兜底方法
//    public String paymentCircuitBreaker_fallback(Integer id){
//        return "id 不能为负数，请稍后再试，o(╥﹏╥)o   id:"+id;
//    }
//    @HystrixCommand(defaultFallback = "paymentCircuitBreaker_fallback",commandProperties = {
//            //意思是10秒内，发送10次请求，失败率达到60%就短路
//            @HystrixProperty(name="circuitBreaker.enabled",value = "true"),//是否开启断路器
//            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
//            @HystrixProperty(name="circuitBreaker.sleepWindowInMillisSeconds",value = "10000"),//时间窗口期 or 时间范围
//            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "60")//失败率达到多少后跳闸
//    })
    //服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {

            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),  //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),   //请求次数
           @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),  //时间范围
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"), //失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker( Integer id){
        if (id < 0){
            throw new RuntimeException("*****id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功,流水号："+serialNumber;
    }
    public String paymentCircuitBreaker_fallback( Integer id){
        return "id 不能负数，请稍候再试,(┬＿┬)/~~     id: " +id;
    }


}
