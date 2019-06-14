package com.liuyanzhao.sens.modules.base.serviceimpl.mybatis;

import com.liuyanzhao.sens.modules.base.dao.mapper.UserRoleMapper;
import com.liuyanzhao.sens.modules.base.entity.Role;
import com.liuyanzhao.sens.modules.base.entity.UserRole;
import com.liuyanzhao.sens.modules.base.service.mybatis.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 言曌
 */
@Service
public class IUserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<Role> findByUserId(String userId) {

        return userRoleMapper.findByUserId(userId);
    }

    @Override
    public List<String> findDepIdsByUserId(String userId) {

        return userRoleMapper.findDepIdsByUserId(userId);
    }
}
