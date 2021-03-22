package com.poxiao.system.aspect;

import com.poxiao.common.core.datasource.DynamicDataSourceContextHolder;
import com.poxiao.common.core.enums.DataSourceType;
import com.poxiao.system.config.DruidProperties;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author qinqi
 * @date 2020/9/14
 */
@Aspect
@Component
@Order(1)
public class DataSourceAspect {

    @Autowired
    private DruidProperties druidProperties;

    @Around("execution(* com.poxiao..*SeviceImpl.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {

        if (!druidProperties.slaveEnable) {
            return point.proceed();
        }
        //获取当前执行的方法名
        String methodName = point.getSignature().getName();
        if (isSlave(methodName)) {
            // 标记为读库,可以自定义选择数据源
            DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.SLAVE.name());
        }else {
            // 标记为写库
            DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.MASTER.name());
        }
        try {
            return point.proceed();
        }finally {
            // 销毁数据源 在执行方法之后
            DynamicDataSourceContextHolder.clearDataSourceType();
        }
    }

    /**
     * 判断是否为读库
     * @param methodName 方法名
     * @return boolean
     */
    private boolean isSlave(String methodName) {
        // 方法名以query、find、get开头的方法名走从库
        return StringUtils.startsWithAny(methodName,new String[]{"get","select","query","find"});
    }
}
