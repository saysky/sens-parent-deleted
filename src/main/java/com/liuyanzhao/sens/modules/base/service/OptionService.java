package com.liuyanzhao.sens.modules.base.service;


import com.liuyanzhao.sens.base.SensBaseService;
import com.liuyanzhao.sens.modules.base.entity.Option;


/**
 * 设置接口
 * @author 言曌
 */
public interface OptionService extends SensBaseService<Option,String> {


    /**
     * 根据设置名称获得设置信息
     * @param optionName 设置名称
     * @return 设置值
     */
    String getOptionValue(String optionName);

    /**
     * 添加设置
     * @param optionName 设置名称
     * @param optionValue 设置值
     */
    void save(String optionName, String optionValue);
}
