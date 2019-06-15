package com.liuyanzhao.sens.modules.base.service;


import com.liuyanzhao.sens.base.SensBaseService;
import com.liuyanzhao.sens.modules.base.entity.User;
import com.liuyanzhao.sens.modules.base.entity.UserRole;

import java.util.List;

/**
 * 用户角色接口
 * @author 言曌
 */
public interface UserRoleService extends SensBaseService<UserRole,String> {

    /**
     * 通过roleId查找
     * @param roleId
     * @return
     */
    List<UserRole> findByRoleId(String roleId);

    /**
     * 通过roleId查找用户
     * @param roleId
     * @return
     */
    List<User> findUserByRoleId(String roleId);

    /**
     * 删除用户角色
     * @param userId
     */
    void deleteByUserId(String userId);
}
