package com.platform.cloud.manager.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 创建时间:2021/9/9 0009
 * 创建人:pmc
 * 描述:
 */
@RestController
@RequestMapping("test")
@Api(tags = {"测试类"})
public class CtrlTest{
    @ApiOperation("测试接口")
    @PostMapping("getTest")
    public void getTest(){

    }
}
