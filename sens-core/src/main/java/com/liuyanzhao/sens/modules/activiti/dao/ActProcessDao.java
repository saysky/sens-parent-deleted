package com.liuyanzhao.sens.modules.activiti.dao;

import com.liuyanzhao.sens.base.SensBaseDao;
import com.liuyanzhao.sens.modules.activiti.entity.ActProcess;

import java.util.List;

/**
 * 流程管理数据处理层
 * @author 言曌
 */
public interface ActProcessDao extends SensBaseDao<ActProcess,String> {

    /**
     * 通过key获取
     * @param processKey
     * @return
     */
    List<ActProcess> findByProcessKey(String processKey);

    /**
     * 通过key和latest获取
     * @param processKey
     * @return
     */
    List<ActProcess> findByProcessKeyAndLatest(String processKey, Boolean latest);

    /**
     * 通过key获取按版本排序
     * @param processKey
     * @return
     */
    ActProcess findTopByProcessKeyOrderByVersionDesc(String processKey);

    /**
     * 通过分类获取
     * @param categoryId
     * @return
     */
    List<ActProcess> findByCategoryId(String categoryId);
}