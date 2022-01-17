package com.platform.cloud.common.core.enums;

import lombok.Getter;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 创建时间:2021/9/18 0018
 * 创建人:pmc
 * 描述:
 */
@Getter
public enum EnumApplications{
    /**
     * 小程序
     */
    APPLETS(EnumApplications.NAME_APPLETS,"APP"),
    /**
     * 网关
     */
    GETWAY(EnumApplications.NAME_GETWAY,"GTW"),
    /**
     * 后台管理
     */
    MANAGER(EnumApplications.NAME_MANAGER,"MGR");

    public static final Map<String,EnumApplications> APPLICATIONS_MAP = Stream.of(values()).collect(Collectors.toMap(EnumApplications::getApplicationName,e->e));
    /** 系统名称 */
    public static final String CLOUND_NAME = "platform";
    /** 小程序服务名称 */
    public static final String NAME_APPLETS = "platform-applets-biz";
    /** 网关服务名称 */
    public static final String NAME_GETWAY = "platform-getway-biz";
    /** 后台管理服务名称 */
    public static final String NAME_MANAGER = "platform-manager-biz";
    /** 基础服务名称 */
    public static final String NAME_BASE = "platform-base-biz";
    /** 用户服务名称 */
    public static final String NAME_USER = "platform-user-biz";

    private String applicationName;
    private String prifix;

    EnumApplications(String applicationName,String prifix){
        this.applicationName = applicationName;
        this.prifix = prifix;
    }
}