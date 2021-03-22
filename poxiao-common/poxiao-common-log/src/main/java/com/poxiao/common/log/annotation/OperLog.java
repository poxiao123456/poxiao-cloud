package com.poxiao.common.log.annotation;

import com.poxiao.common.log.enums.BusinessType;
import com.poxiao.common.log.enums.OperatorType;

import java.lang.annotation.*;

/**
 * @author qinqi
 * @date 2020/9/17
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperLog {

    /**
     * 模块
     */
    String title() default "";
    /**
     * 功能
     */
    BusinessType businessType() default BusinessType.OTHER;

    /**
     * 操作人类别
     */
    OperatorType operatorType() default OperatorType.MANAGE;

    /**
     * 是否保存请求的参数
     */
    boolean isSaveRequestData() default true;

}
