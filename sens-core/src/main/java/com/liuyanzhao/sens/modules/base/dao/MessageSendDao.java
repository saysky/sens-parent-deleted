package com.liuyanzhao.sens.modules.base.dao;

import com.liuyanzhao.sens.base.SensBaseDao;
import com.liuyanzhao.sens.modules.base.entity.MessageSend;

/**
 * 消息发送数据处理层
 * @author 言曌
 */
public interface MessageSendDao extends SensBaseDao<MessageSend,String> {

    /**
     * 通过消息id删除
     * @param messageId
     */
    void deleteByMessageId(String messageId);
}