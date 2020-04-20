package com.wkjy.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.wkjy.springcloud.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
//全局默认的FallBack , 如果和业务有关的，需要单独写个FallBack就直接在HystrixCommand上设置fallbackMethod方法和commandProperties
//好处：通用的fallback和独享的fallback各自分开，避免了代码膨胀，合理减少了代码量
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderController {

    @Resource
    private OrderService service;





    @GetMapping(value = "/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_Ok(@PathVariable("id")Integer id){
        String result = service.paymentInfo_ok(id);
        return result;
    }

    /**
     * 问题：
     *      随着方法的增多，每一个方法都需要一个fallback方法，100个就有100个fallback,而其中就有很多相同的
     *      有什么方法可以避免代码的冗余？
     *      可以使用DefaultProperties注解，配置全局打的fallback,然后个别不同的自己去另外设置
     */


    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
    //单独设定的Fallback
//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutFallback",commandProperties = {
//        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")})
    @HystrixCommand
    public String paymentInfo_TimeOut(@PathVariable("id")Integer id){
        //int age =10/0;
        String result = service.paymentInfo_TimeOut(id);
        return result;
    }

    public String paymentInfo_TimeOutFallback(Integer id){
        return "我是消费者80，对支付系统繁忙请10秒钟后再试或者自己运行出错检查自己，o(╥﹏╥)o";
    }

    //用来做全局的fallback
    public String payment_Global_FallbackMethod(){
        return "Global异常信息处理，稍后再试，/(ToT)/~~";
    }
}
