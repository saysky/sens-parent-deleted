package com.liuyanzhao.sens.modules.activiti.dao;

import com.liuyanzhao.sens.base.SensBaseDao;
import com.liuyanzhao.sens.modules.activiti.entity.ActNode;

import java.util.List;

/**
 * 流程节点用户数据处理层
 * @author 言曌
 */
public interface ActNodeDao extends SensBaseDao<ActNode,String> {

    /**
     * 通过nodeId获取
     * @param nodeId
     * @param type
     * @return
     */
    List<ActNode> findByNodeIdAndType(String nodeId, Integer type);

    /**
     * 通过nodeId删除
     * @param nodeId
     */
    void deleteByNodeId(String nodeId);

    /**
     * 通过relateId删除
     * @param relateId
     */
    void deleteByRelateId(String relateId);
}