package com.poxiao.api.system.util;

import com.poxiao.api.system.model.SysUser;
import com.poxiao.common.core.utils.security.Md5Utils;

public class PasswordUtil
{
    public static boolean matches(SysUser user, String newPassword)
    {
        return user.getPassword().equals(encryptPassword(user.getLoginName(), newPassword, user.getSalt()));
    }

    public static String encryptPassword(String username, String password, String salt)
    {
        return Md5Utils.hash(username + password + salt);
    }
}