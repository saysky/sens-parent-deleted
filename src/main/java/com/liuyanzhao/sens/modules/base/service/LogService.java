package com.liuyanzhao.sens.modules.base.service;


import com.liuyanzhao.sens.base.SensBaseService;
import com.liuyanzhao.sens.common.vo.SearchVo;
import com.liuyanzhao.sens.modules.base.entity.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 日志接口
 * @author 言曌
 */
public interface LogService extends SensBaseService<Log,String> {

    /**
     * 分页搜索获取日志
     * @param type
     * @param key
     * @param searchVo
     * @param pageable
     * @return
     */
    Page<Log> findByConfition(Integer type, String key, SearchVo searchVo, Pageable pageable);

    /**
     * 删除所有
     */
    void deleteAll();
}
