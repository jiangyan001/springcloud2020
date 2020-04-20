package com.wkjy.springcloud.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderZkController {
    @Value("${server.port}")
    private String serverPort;
    @Autowired
    private RestTemplate restTemplate;

    public static final String INVOKE_URL = "http://cloud-provider-server";
    @RequestMapping(value = "/consumer/order/zk")
    public String OrderZk(){
        String result = restTemplate.getForObject(INVOKE_URL+"/payment/zk",String.class);
        return result;
    }
}
