package com.platform.cloud.base.client;

import feign.hystrix.FallbackFactory;

/**
 * 创建时间:2021/11/13
 * 创建人:pmc
 * 描述:
 */
public class FactoryDictionary implements FallbackFactory<ClientDictionary>{
    @Override
    public ClientDictionary create(Throwable throwable){
        return new FallBackDictionary(throwable);
    }
}