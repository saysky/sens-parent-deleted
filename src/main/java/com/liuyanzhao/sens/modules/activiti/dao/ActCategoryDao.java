package com.liuyanzhao.sens.modules.activiti.dao;

import com.liuyanzhao.sens.base.SensBaseDao;
import com.liuyanzhao.sens.modules.activiti.entity.ActCategory;

import java.util.List;

/**
 * 流程分类数据处理层
 * @author 言曌
 */
public interface ActCategoryDao extends SensBaseDao<ActCategory,String> {

    /**
     * 通过父id获取
     * @param parentId
     * @return
     */
    List<ActCategory> findByParentIdOrderBySortOrder(String parentId);

    /**
     * 通过名称模糊搜索
     * @param title
     * @return
     */
    List<ActCategory> findByTitleLikeOrderBySortOrder(String title);
}