package com.wkjy.springcloud.service;

import com.wkjy.springcloud.entity.CommonResult;
import com.wkjy.springcloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

@Component
@FeignClient(value = "cloud-payment-service")
public interface PaymentFeignService {
    @RequestMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment);
    @RequestMapping(value = "/payment/update")
    public CommonResult update(@RequestBody Payment payment);
    @RequestMapping(value = "/payment/del")
    public CommonResult del(@RequestBody Long id);
    @RequestMapping(value = "/payment/selectAll")
    public CommonResult selectAll();
    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout();
}
