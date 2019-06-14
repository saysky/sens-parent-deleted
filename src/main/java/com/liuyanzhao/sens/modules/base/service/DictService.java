package com.liuyanzhao.sens.modules.base.service;

import com.liuyanzhao.sens.base.SensBaseService;
import com.liuyanzhao.sens.modules.base.entity.Dict;

import java.util.List;

/**
 * 字典接口
 * @author 言曌
 */
public interface DictService extends SensBaseService<Dict,String> {

    /**
     * 排序获取全部
     * @return
     */
    List<Dict> findAllOrderBySortOrder();

    /**
     * 通过type获取
     * @param type
     * @return
     */
    Dict findByType(String type);

    /**
     * 模糊搜索
     * @param key
     * @return
     */
    List<Dict> findByTitleOrTypeLike(String key);
}