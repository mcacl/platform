package com.platform.cloud.common.core.constant.fallback;

/**
 * 创建时间:2021/11/13
 * 创建人:pmc
 * 描述:熔断常量
 */
public interface ConstantFallBack{
    /** 熔断失败状态码 */
    public final Integer FALLBACK_FAILED_CODE = 900;
    public final String FALLBACK_FAILED_MSG = "远程服务调用失败!";
}