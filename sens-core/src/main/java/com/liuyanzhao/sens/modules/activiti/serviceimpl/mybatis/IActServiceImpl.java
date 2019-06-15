package com.liuyanzhao.sens.modules.activiti.serviceimpl.mybatis;

import com.liuyanzhao.sens.common.exception.SensException;
import com.liuyanzhao.sens.modules.activiti.dao.mapper.ActMapper;
import com.liuyanzhao.sens.modules.activiti.service.mybatis.IActService;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 言曌
 */
@Service
public class IActServiceImpl implements IActService {

    @Autowired
    private ActMapper actMapper;

    @Override
    public Integer deleteBusiness(String table, String id) {

        if(StrUtil.isBlank(table)||StrUtil.isBlank(id)){
            throw new SensException("关联业务表名或id为空");
        }
        return actMapper.deleteBusiness(table, id);
    }
}
