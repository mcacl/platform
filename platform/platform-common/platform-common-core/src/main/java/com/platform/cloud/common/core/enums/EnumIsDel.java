package com.platform.cloud.common.core.enums;

/**
 * 创建时间:2021/9/7
 * 创建人:pmc
 * 描述:
 */
public enum EnumIsDel{
    NORMAL("0","正常"),DELETE("1","已删除");

    private String code;
    private String msg;

    EnumIsDel(String code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public String getCode(){
        return code;
    }

    public void setCode(String code){
        this.code = code;
    }

    public String getMsg(){
        return msg;
    }

    public void setMsg(String msg){
        this.msg = msg;
    }
}
