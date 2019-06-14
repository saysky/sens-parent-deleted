package com.liuyanzhao.sens.modules.activiti.serviceimpl;

import com.liuyanzhao.sens.common.constant.ActivitiConstant;
import com.liuyanzhao.sens.modules.activiti.dao.ActNodeDao;
import com.liuyanzhao.sens.modules.activiti.entity.ActNode;
import com.liuyanzhao.sens.modules.activiti.service.ActNodeService;
import com.liuyanzhao.sens.modules.base.dao.DepartmentDao;
import com.liuyanzhao.sens.modules.base.dao.RoleDao;
import com.liuyanzhao.sens.modules.base.dao.UserDao;
import com.liuyanzhao.sens.modules.base.entity.Department;
import com.liuyanzhao.sens.modules.base.entity.Role;
import com.liuyanzhao.sens.modules.base.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 流程节点用户接口实现
 * @author 言曌
 */
@Slf4j
@Service
@Transactional
public class ActNodeServiceImpl implements ActNodeService {

    @Autowired
    private ActNodeDao actNodeDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public ActNodeDao getRepository() {
        return actNodeDao;
    }

    @Override
    public List<User> findUserByNodeId(String nodeId) {

        List<ActNode> listNode = actNodeDao.findByNodeIdAndType(nodeId, ActivitiConstant.NODE_USER);
        List<User> list = new ArrayList<>();
        listNode.forEach(e -> {
            User u = userDao.getOne(e.getRelateId());
            list.add(u);
        });
        return list;
    }

    @Override
    public List<Role> findRoleByNodeId(String nodeId) {

        List<ActNode> listNode = actNodeDao.findByNodeIdAndType(nodeId, ActivitiConstant.NODE_ROLE);
        List<Role> list = new ArrayList<>();
        listNode.forEach(e -> {
            Role r = roleDao.getOne(e.getRelateId());
            list.add(r);
        });
        return list;
    }

    @Override
    public List<Department> findDepartmentByNodeId(String nodeId) {

        List<ActNode> listNode = actNodeDao.findByNodeIdAndType(nodeId, ActivitiConstant.NODE_DEPARTMENT);
        List<Department> list = new ArrayList<>();
        listNode.forEach(e -> {
            Department d = departmentDao.getOne(e.getRelateId());
            list.add(d);
        });
        return list;
    }

    @Override
    public List<String> findDepartmentIdsByNodeId(String nodeId) {

        List<ActNode> listNode = actNodeDao.findByNodeIdAndType(nodeId, ActivitiConstant.NODE_DEPARTMENT);
        List<String> list = new ArrayList<>();
        listNode.forEach(e -> {
            list.add(e.getRelateId());
        });
        return list;
    }

    @Override
    public void deleteByNodeId(String nodeId) {

        actNodeDao.deleteByNodeId(nodeId);
    }

    @Override
    public void deleteByRelateId(String relateId) {

        actNodeDao.deleteByRelateId(relateId);
    }
}