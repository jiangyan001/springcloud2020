package com.wkjy.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
public class PaymentController {


    @Value("${server.port}")
    private String serverPort;

    @RequestMapping(value = "/payment/zk")
    public String paymentzk(){
        return "springCloud with Zookeeper :"+serverPort+"\t"+ UUID.randomUUID().toString();
    }
}
/**
 * <dependency>
 *             <groupId>com.sc2020</groupId>
 *             <artifactId>cloud-api-commons</artifactId>
 *             <version>1.0-SNAPSHOT</version>
 *         </dependency>
 *         <dependency>
 *             <groupId>org.springframework.boot</groupId>
 *             <artifactId>spring-boot-starter-web</artifactId>
 *         </dependency>
 *         <dependency>
 *             <groupId>org.springframework.boot</groupId>
 *             <artifactId>spring-boot-starter-actuator</artifactId>
 *         </dependency>
 *         <dependency>
 *             <groupId>org.mybatis.spring.boot</groupId>
 *             <artifactId>mybatis-spring-boot-starter</artifactId>
 *         </dependency>
 *         <dependency>
 *             <groupId>com.alibaba</groupId>
 *             <artifactId>druid-spring-boot-starter</artifactId>
 *             <version>1.1.10</version>
 *         </dependency>
 *         <dependency>
 *             <groupId>mysql</groupId>
 *             <artifactId>mysql-connector-java</artifactId>
 *         </dependency>
 *         <dependency>
 *             <groupId>org.springframework.boot</groupId>
 *             <artifactId>spring-boot-starter-jdbc</artifactId>
 *         </dependency>
 *         <dependency>
 *             <groupId>org.springframework.boot</groupId>
 *             <artifactId>spring-boot-starter-test</artifactId>
 *             <scope>test</scope>
 *         </dependency>
 *         <dependency>
 *             <groupId>org.springframework.boot</groupId>
 *             <artifactId>spring-boot-devtools</artifactId>
 *             <scope>runtime</scope>
 *             <optional>true</optional>
 *         </dependency>
 *         <!--   引入zookeeper客户端     -->
 *         <dependency>
 *             <groupId>org.springframework.cloud</groupId>
 *             <artifactId>spring-cloud-starter-zookeeper-discovery</artifactId>
 *             <exclusions>
 *                 <exclusion>
 *                     <groupId>org.apache.zookeeper</groupId>
 *                     <artifactId>zookeeper</artifactId>
 *                 </exclusion>
 *             </exclusions>
 *         </dependency>
 *         <dependency>
 *             <groupId>org.apache.zookeeper</groupId>
 *             <artifactId>zookeeper</artifactId>
 *             <version>3.5.7</version>
 *         </dependency>
 */