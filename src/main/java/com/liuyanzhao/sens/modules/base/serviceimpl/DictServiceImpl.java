package com.liuyanzhao.sens.modules.base.serviceimpl;

import com.liuyanzhao.sens.modules.base.dao.DictDao;
import com.liuyanzhao.sens.modules.base.entity.Dict;
import com.liuyanzhao.sens.modules.base.service.DictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 字典接口实现
 * @author 言曌
 */
@Slf4j
@Service
@Transactional
public class DictServiceImpl implements DictService {

    @Autowired
    private DictDao dictDao;

    @Override
    public DictDao getRepository() {
        return dictDao;
    }

    @Override
    public List<Dict> findAllOrderBySortOrder() {

        return dictDao.findAllOrderBySortOrder();
    }

    @Override
    public Dict findByType(String type) {

        List<Dict> list = dictDao.findByType(type);
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<Dict> findByTitleOrTypeLike(String key) {

        return dictDao.findByTitleOrTypeLike(key);
    }
}