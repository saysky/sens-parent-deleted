package com.liuyanzhao.sens.modules.base.dao;

import com.liuyanzhao.sens.base.SensBaseDao;
import com.liuyanzhao.sens.modules.base.entity.social.Weibo;

/**
 * 微博登录数据处理层
 * @author 言曌
 */
public interface WeiboDao extends SensBaseDao<Weibo,String> {

    /**
     * 通过openId获取
     * @param openId
     * @return
     */
    Weibo findByOpenId(String openId);

    /**
     * 通过username获取
     * @param username
     * @return
     */
    Weibo findByRelateUsername(String username);

    /**
     * 通过username删除
     * @param username
     */
    void deleteByUsername(String username);
}