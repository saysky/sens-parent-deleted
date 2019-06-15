package com.liuyanzhao.sens.modules.activiti.service;

import com.liuyanzhao.sens.base.SensBaseService;
import com.liuyanzhao.sens.modules.activiti.entity.ActCategory;

import java.util.List;

/**
 * 流程分类接口
 * @author 言曌
 */
public interface ActCategoryService extends SensBaseService<ActCategory,String> {

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