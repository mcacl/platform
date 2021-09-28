package com.platform.cloud.common.core.entity;

import cn.hutool.core.text.StrFormatter;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 创建时间:2021/9/28 0028
 * 创建人:pmc
 * 描述:
 */
@Data
@Accessors(chain = true)//set方法返回当前对象
public class PResponse<DATA> implements Serializable{
    @ApiModelProperty("请求状态")
    private Boolean status;

    @ApiModelProperty(value = "状态码")
    private String statusCode;

    @ApiModelProperty("消息")
    private String statusMessage;

    @ApiModelProperty("返回数据")
    private DATA data;

    public static <D> PResponse<D> success(){
        PResponse<D> response = new PResponse<>();
        response.setStatus(true);
        response.setStatusCode(0);
        return response;
    }

    public static <D> PResponse<D> success(String message){
        PResponse<D> success = PResponse.<D>success();
        if(message != null){
            success.setStatusMessage(message);
        }
        return success;
    }

    public PResponse<DATA> setStatusCode(Integer statusCode){
        this.statusCode = calcCode(statusCode);
        return this;
    }

    private String calcCode(Integer statusCode){
        if(statusCode == null || statusCode < 0){
            this.setStatusMessage(StrFormatter.format("statusCode错误(statusCode:{})",statusCode));
            return "00001";
        }else if(statusCode > 9999){
            return String.valueOf(statusCode);
        }else{
            String codeStr = String.valueOf(statusCode);
            return StringUtils.leftPad(codeStr,5,"0");
        }
    }
}
