package com.wkjy.springcloud.controller;

import com.wkjy.springcloud.entity.CommonResult;
import com.wkjy.springcloud.entity.Payment;
import com.wkjy.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.Stack;

@RestController
@Slf4j
public class PaymentFeignController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @RequestMapping(value = "/consumer/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        return paymentFeignService.create(payment);
    }
    @RequestMapping(value = "/consumer/payment/update")
    public CommonResult update(@RequestBody Payment payment){
        return paymentFeignService.update(payment);
    }

    @RequestMapping(value = "/consumer/payment/del")
    public CommonResult del(@RequestBody Long id){
        return paymentFeignService.del(id);
    }
    @RequestMapping(value = "/consumer/payment/selectAll")
    public CommonResult selectAll(){
        return  paymentFeignService.selectAll();
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout(){

        //OpenFeign默认等待一秒钟，超过后报错
        //为了解决这个问题，需要设置Robbin开启OpenFeign的客户端超市控制在·
        return  paymentFeignService.paymentFeignTimeout();
    }
}
