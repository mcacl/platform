package com.platform.cloud.common.core.aspect.notes;

import java.lang.annotation.*;

/**
 * 方法作者及功能标注
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface Notes{
    /**
     * 说明
     * @return
     */
    String value() default "";

    /**
     * 方法参数
     * @return
     */
    String[] params() default {};
}
