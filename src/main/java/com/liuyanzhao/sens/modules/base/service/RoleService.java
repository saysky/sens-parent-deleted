package com.liuyanzhao.sens.modules.base.service;


import com.liuyanzhao.sens.base.SensBaseService;
import com.liuyanzhao.sens.modules.base.entity.Role;

import java.util.List;

/**
 * 角色接口
 * @author 言曌
 */
public interface RoleService extends SensBaseService<Role,String> {

    /**
     * 获取默认角色
     * @param defaultRole
     * @return
     */
    List<Role> findByDefaultRole(Boolean defaultRole);
}
