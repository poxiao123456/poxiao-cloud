package com.poxiao.common.log.event;

import com.poxiao.api.system.model.SysOperLog;
import org.springframework.context.ApplicationEvent;

/**
 * @author qinqi
 * @date 2020/9/17
 */
public class SysOperLogEvent extends ApplicationEvent {

    private static final long serialVersionUID = 8905017895058642111L;

    public SysOperLogEvent(SysOperLog source) {
        super(source);
    }
}
