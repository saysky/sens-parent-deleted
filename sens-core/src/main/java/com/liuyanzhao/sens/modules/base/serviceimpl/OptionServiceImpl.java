package com.liuyanzhao.sens.modules.base.serviceimpl;

import com.liuyanzhao.sens.modules.base.dao.OptionDao;
import com.liuyanzhao.sens.modules.base.entity.Option;
import com.liuyanzhao.sens.modules.base.service.OptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 角色接口实现
 *
 * @author 言曌
 */
@Slf4j
@Service
@Transactional
public class OptionServiceImpl implements OptionService {

    @Autowired
    private OptionDao optionDao;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public OptionDao getRepository() {
        return optionDao;
    }


    @Override
    public String getOptionValue(String optionName) {
        String optionValue = redisTemplate.opsForValue().get(optionName);
        if (null == optionValue) {
            optionValue = optionDao.getOptionValue(optionName);
            if (null != optionValue) {
                redisTemplate.opsForValue().set(optionName, optionValue);
            }
        }
        return optionValue;
    }

    @Override
    public void save(String optionName, String optionValue) {
        optionDao.save(new Option(optionName, optionValue));
        redisTemplate.opsForValue().set(optionName, optionValue);
    }
}
