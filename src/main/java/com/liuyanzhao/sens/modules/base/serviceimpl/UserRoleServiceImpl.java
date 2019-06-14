package com.liuyanzhao.sens.modules.base.serviceimpl;

import com.liuyanzhao.sens.common.constant.CommonConstant;
import com.liuyanzhao.sens.modules.base.dao.UserDao;
import com.liuyanzhao.sens.modules.base.dao.UserRoleDao;
import com.liuyanzhao.sens.modules.base.entity.User;
import com.liuyanzhao.sens.modules.base.entity.UserRole;
import com.liuyanzhao.sens.modules.base.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户角色接口实现
 * @author 言曌
 */
@Slf4j
@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private UserDao userDao;

    @Override
    public UserRoleDao getRepository() {
        return userRoleDao;
    }

    @Override
    public List<UserRole> findByRoleId(String roleId) {
        return userRoleDao.findByRoleId(roleId);
    }

    @Override
    public List<User> findUserByRoleId(String roleId) {

        List<UserRole> userRoleList = userRoleDao.findByRoleId(roleId);
        List<User> list = new ArrayList<>();
        for(UserRole ur : userRoleList){
            User u = userDao.getOne(ur.getUserId());
            if(u!=null&&CommonConstant.USER_STATUS_NORMAL.equals(u.getStatus())){
                list.add(u);
            }
        }
        return list;
    }

    @Override
    public void deleteByUserId(String userId) {
        userRoleDao.deleteByUserId(userId);
    }
}
