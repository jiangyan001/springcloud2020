package com.wkjy.springcloud.config;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {


    /**
     * 配置了一个id为path_route的路由规则，
     * 当访问地址http://localhost:9527/guonei时会自动转发到地址：http://news.baidu.com/guonei
     * @param reRouteLocatorBuilder
     * @return
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder reRouteLocatorBuilder){
        RouteLocatorBuilder.Builder routes = reRouteLocatorBuilder.routes();

        routes.route(
                "path_route",
                //r.path  配置的是网关，localhost:9527/guonei    就能访问到后面的uri
                r ->r.path("/guonei").uri("http://news.baidu.com/guonei")
        ).build();

        return routes.build();
    }

    @Bean
    public RouteLocator customRouteLocator1(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_route1",r ->r.path("/guoji").uri("http://news.baidu.com/guoji")).build();
        return routes.build();
    }
}
