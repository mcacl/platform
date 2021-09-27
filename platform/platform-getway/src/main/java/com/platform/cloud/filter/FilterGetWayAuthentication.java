package com.platform.cloud.filter;

import com.platform.cloud.common.core.utils.UtilIP;
import com.platform.cloud.properties.PropertisGetWay;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 创建时间:2021/9/15 0015
 * 创建人:pmc
 * 描述:
 */
@Slf4j
@Component
public class FilterGetWayAuthentication implements GlobalFilter, Ordered{
    private static final AntPathMatcher antPathMatcher = new AntPathMatcher();
    private final PropertisGetWay propertisGetWay;

    public FilterGetWayAuthentication(PropertisGetWay propertisGetWay){
        this.propertisGetWay = propertisGetWay;
    }
    //更具getway配置通过或拦截
   /* @Override
    public Mono<Void> filter(ServerWebExchange exchange,GatewayFilterChain chain){
        String curUrl = exchange.getRequest().getPath().value();
        List<String> authheaders = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION);
        //配置可通过的url
        List<String> exUrl = propertisGetWay.getSecurity().getExcludeUrls();
        boolean chenckUrl = exUrl.stream().anyMatch(url->{
            boolean match = antPathMatcher.match(url,curUrl);
            if(match){
                log.info("当前路由:{}匹配到:{}配置规则",curUrl,url);
            }
            return match;
        });
        if(chenckUrl){
            return chain.filter(exchange);//通过
        }
        if(null == authheaders || authheaders.isEmpty()){
            return this.response401(exchange);//无请求头
        }else{
            return this.response401(exchange);
        }
    }*/

    @Override
    public int getOrder(){
        return HIGHEST_PRECEDENCE + 1;
    }

    private Mono<Void> response401(ServerWebExchange exchange){
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange,GatewayFilterChain chain){
        String ip = UtilIP.getRealIpAddress(exchange.getRequest());
        log.info("当前请求IP>>{}",ip);
        return chain.filter(exchange);
    }
}
