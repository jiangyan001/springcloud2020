package com.wkjy.springcloud.controller;

import com.wkjy.springcloud.entity.CommonResult;
import com.wkjy.springcloud.entity.Payment;
import com.wkjy.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.Stack;

@RestController// Controller
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService service;

    @Value("${server.port}")
    private String serverPort;

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
    }


    @RequestMapping(value = "/payment/lb")
    public String getServerPort(){
        return serverPort;
    }
}
