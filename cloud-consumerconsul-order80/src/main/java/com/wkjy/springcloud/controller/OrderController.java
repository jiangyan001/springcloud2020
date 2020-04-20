package com.wkjy.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderController {
    @Value("${server.port}")
    private String serverPort;

    public final String PAYMENT_URL = "http://consul-provider-payment";
    @Autowired
    private RestTemplate restTemplate;
    @RequestMapping("/consumer/payment/consul")
    public String paymentConsul(){
        String result = restTemplate.getForObject(PAYMENT_URL+"/payment/consul",String.class);
        return result;
    }

}
