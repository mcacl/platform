package com.platform.cloud.user.xxljob;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

/**
 * 创建时间:2021/12/14 0014
 * 创建人:pmc
 * 描述:
 */
@Component
public class JobUser{
    @XxlJob("jobUser")
    public void jobUser(){
        String param = XxlJobHelper.getJobParam();
        XxlJobHelper.log("XXL-JOB, Hello World.{}",param);
        for(int i = 0; i < 5; i++){
            XxlJobHelper.log("beat at:" + i);
        }
        XxlJobHelper.handleSuccess("任务调度成功");
    }
}