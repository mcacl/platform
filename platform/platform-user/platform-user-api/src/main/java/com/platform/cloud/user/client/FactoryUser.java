package com.platform.cloud.user.client;

import feign.hystrix.FallbackFactory;

/**
 * 创建时间:2022/1/17 0017
 * 创建人:pmc
 * 描述:
 */
public class FactoryUser implements FallbackFactory<ClientUser>{
    @Override
    public ClientUser create(Throwable throwable){
        return new FallbackUser(throwable);
    }
}