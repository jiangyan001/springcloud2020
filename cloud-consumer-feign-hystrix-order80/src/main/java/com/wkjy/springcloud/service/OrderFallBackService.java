package com.wkjy.springcloud.service;


import org.springframework.stereotype.Component;

/**
 * 这个类是处理宕机的异常的
 */
@Component
public class OrderFallBackService implements OrderService {
    @Override
    public String paymentInfo_ok(Integer id) {
        return "paymentInfo_ok暂时不可用，请稍后再试！";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "paymentInfo_TimeOut暂不可用，请稍后再试";
    }
}
