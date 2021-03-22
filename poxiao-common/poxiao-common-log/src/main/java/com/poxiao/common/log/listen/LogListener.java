package com.poxiao.common.log.listen;

import com.poxiao.api.system.feign.RemoteLogService;
import com.poxiao.api.system.model.SysLogininfor;
import com.poxiao.api.system.model.SysOperLog;
import com.poxiao.common.log.event.SysLogininforEvent;
import com.poxiao.common.log.event.SysOperLogEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author qinqi
 * @date 2020/9/17
 * 异步监听日志事件
 */
@EnableAsync
@Slf4j
@Component
public class LogListener {


    @Resource
    private RemoteLogService remoteLogService;

    @Async
    @Order
    @EventListener(SysOperLogEvent.class)
    public void listenOperLog(SysOperLogEvent event) {
        SysOperLog sysOperLog = (SysOperLog) event.getSource();
        remoteLogService.insertOperlog(sysOperLog);
        log.info("远程操作日志记录成功：{}", sysOperLog);
    }

    @Async
    @Order
    @EventListener(SysLogininforEvent.class)
    public void listenLoginifor(SysLogininforEvent event)
    {
        SysLogininfor sysLogininfor = (SysLogininfor) event.getSource();
        remoteLogService.insertLoginlog(sysLogininfor);
        log.info("远程访问日志记录成功：{}", sysLogininfor);
    }
}
