package com.liuyanzhao.sens.modules.base.dao.elasticsearch;

import com.liuyanzhao.sens.modules.base.entity.elasticsearch.EsLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * @author 言曌
 */
public interface EsLogDao extends ElasticsearchRepository<EsLog, String> {

    /**
     * 通过类型获取
     * @param type
     * @return
     */
    Page<EsLog> findByLogType(Integer type, Pageable pageable);
}
