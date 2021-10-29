package com.platform.cloud.common.data.config;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.platform.cloud.common.core.entity.base.EntityBaseSnowflakeId;
import com.platform.cloud.common.core.utils.UtilSnowflakeId;
import lombok.var;
import org.apache.commons.lang3.StringUtils;

/**
 * 创建时间:2021/9/7 0007
 * 创建人:pmc
 * 描述:雪花id 生成
 */
public class PrefixSnowflakeIdentifierGenerator implements IdentifierGenerator{
    @Override
    public Number nextId(Object entity){
        return UtilSnowflakeId.generateId();
    }

    @Override
    public String nextUUID(Object entity){
        if(entity instanceof EntityBaseSnowflakeId){
            var v2 = (EntityBaseSnowflakeId) entity;
            var sb = new StringBuilder();

            var prefix = v2.idPrefix();
            var idOverride = v2.idOverride();
            if(StringUtils.isNotBlank(prefix)){
                sb.append(prefix);
                sb.append("-");
            }
            if(StringUtils.isNotBlank(idOverride)){
                sb.append(idOverride);
            }else{
                sb.append(nextId(entity));
            }
            return sb.toString();
        }

        return nextId(entity).toString();
    }
}