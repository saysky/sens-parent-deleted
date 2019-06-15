package com.liuyanzhao.sens.modules.activiti.serviceimpl;

import com.liuyanzhao.sens.modules.activiti.dao.ActCategoryDao;
import com.liuyanzhao.sens.modules.activiti.entity.ActCategory;
import com.liuyanzhao.sens.modules.activiti.service.ActCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 流程分类接口实现
 * @author 言曌
 */
@Slf4j
@Service
@Transactional
public class ActCategoryServiceImpl implements ActCategoryService {

    @Autowired
    private ActCategoryDao actCategoryDao;

    @Override
    public ActCategoryDao getRepository() {
        return actCategoryDao;
    }

    @Override
    public List<ActCategory> findByParentIdOrderBySortOrder(String parentId) {

        return actCategoryDao.findByParentIdOrderBySortOrder(parentId);
    }

    @Override
    public List<ActCategory> findByTitleLikeOrderBySortOrder(String title) {

        return actCategoryDao.findByTitleLikeOrderBySortOrder(title);
    }
}