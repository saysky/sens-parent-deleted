package com.liuyanzhao.sens.modules.base.service;

import com.liuyanzhao.sens.base.SensBaseService;
import com.liuyanzhao.sens.modules.base.entity.QuartzJob;

import java.util.List;

/**
 * 定时任务接口
 * @author 言曌
 */
public interface QuartzJobService extends SensBaseService<QuartzJob,String> {

    /**
     * 通过类名获取
     * @param jobClassName
     * @return
     */
    List<QuartzJob> findByJobClassName(String jobClassName);
}