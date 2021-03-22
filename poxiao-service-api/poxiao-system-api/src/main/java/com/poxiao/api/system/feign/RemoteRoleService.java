package com.poxiao.api.system.feign;

import com.poxiao.api.system.feign.factory.RemoteRoleFallbackFactory;
import com.poxiao.api.system.model.SysRole;
import com.poxiao.common.core.constant.ServiceNameConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 角色 Feign服务层
 * 
 * @author zmr
 * @date 2019-05-20
 */
@FeignClient(name = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteRoleFallbackFactory.class)
public interface RemoteRoleService
{
    @GetMapping("role/get/{roleId}")
    public SysRole selectSysRoleByRoleId(@PathVariable("roleId") long roleId);
}
