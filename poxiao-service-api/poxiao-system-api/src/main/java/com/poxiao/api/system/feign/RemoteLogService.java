package com.poxiao.api.system.feign;

import com.poxiao.api.system.feign.factory.RemoteLogFallbackFactory;
import com.poxiao.api.system.model.SysLogininfor;
import com.poxiao.api.system.model.SysOperLog;
import com.poxiao.common.core.constant.ServiceNameConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 日志Feign服务层
 * 
 * @author zmr
 * @date 2019-05-20
 */
@FeignClient(name = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteLogFallbackFactory.class)
public interface RemoteLogService
{
    @PostMapping("operLog/save")
    public void insertOperlog(@RequestBody SysOperLog operLog);

    @PostMapping("logininfor/save")
    public void insertLoginlog(@RequestBody SysLogininfor logininfor);
}
