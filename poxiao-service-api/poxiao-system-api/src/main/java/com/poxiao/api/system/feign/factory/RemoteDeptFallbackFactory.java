package com.poxiao.api.system.feign.factory;

import com.poxiao.api.system.feign.RemoteDeptService;
import com.poxiao.api.system.model.SysDept;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RemoteDeptFallbackFactory implements FallbackFactory<RemoteDeptService>
{/* (non-Javadoc)
  * @see feign.hystrix.FallbackFactory#create(java.lang.Throwable)
  */
    @Override
    public RemoteDeptService create(Throwable throwable)
    {
        log.error(throwable.getMessage());
        return new RemoteDeptService()
        {

            @Override
            public SysDept selectSysDeptByDeptId(long deptId)
            {
                return null;
            }
        };
    }
}
