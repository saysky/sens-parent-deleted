package com.liuyanzhao.sens.modules.base.dao;

import com.liuyanzhao.sens.base.SensBaseDao;
import com.liuyanzhao.sens.modules.base.entity.Option;
import org.springframework.data.jpa.repository.Query;

/**
 * 系统设置数据处理层
 * @author 言曌
 */
public interface OptionDao extends SensBaseDao<Option,String> {

    /**
     * 根据设置名获得设置
     * @param optionName 设置名
     * @return 设置
     */
    @Query(value = "select option_value from t_option o where o.option_name = ?1", nativeQuery = true)
    String getOptionValue(String optionName);
}
