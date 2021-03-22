package com.poxiao.common.log.event;

import com.poxiao.api.system.model.SysLogininfor;
import org.springframework.context.ApplicationEvent;

/**
 * @author qinqi
 * @date 2020/9/17
 */
public class SysLogininforEvent extends ApplicationEvent {

    private static final long serialVersionUID = -9084676463718966036L;

    public SysLogininforEvent(SysLogininfor source) {
        super(source);
    }
}
