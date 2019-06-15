package com.liuyanzhao.sens.modules.activiti.service;

import com.liuyanzhao.sens.base.SensBaseService;
import com.liuyanzhao.sens.common.vo.SearchVo;
import com.liuyanzhao.sens.modules.activiti.entity.ActBusiness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 业务申请接口
 * @author 言曌
 */
public interface ActBusinessService extends SensBaseService<ActBusiness,String> {

    /**
     * 多条件分页获取申请列表
     * @param actBusiness
     * @param searchVo
     * @param pageable
     * @return
     */
    Page<ActBusiness> findByCondition(ActBusiness actBusiness, SearchVo searchVo, Pageable pageable);
}