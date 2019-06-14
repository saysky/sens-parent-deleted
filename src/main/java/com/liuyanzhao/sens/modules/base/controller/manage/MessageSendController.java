package com.liuyanzhao.sens.modules.base.controller.manage;

import com.liuyanzhao.sens.base.SensBaseController;
import com.liuyanzhao.sens.common.utils.PageUtil;
import com.liuyanzhao.sens.common.utils.ResultUtil;
import com.liuyanzhao.sens.common.vo.PageVo;
import com.liuyanzhao.sens.common.vo.Result;
import com.liuyanzhao.sens.modules.base.entity.Message;
import com.liuyanzhao.sens.modules.base.entity.MessageSend;
import com.liuyanzhao.sens.modules.base.entity.User;
import com.liuyanzhao.sens.modules.base.service.MessageSendService;
import com.liuyanzhao.sens.modules.base.service.MessageService;
import com.liuyanzhao.sens.modules.base.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author 言曌
 */
@Slf4j
@RestController
@Api(description = "消息发送管理接口")
@RequestMapping("/sens/messageSend")
@Transactional
public class MessageSendController extends SensBaseController<MessageSend, String> {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageSendService messageSendService;

    @Override
    public MessageSendService getService() {
        return messageSendService;
    }

    @RequestMapping(value = "/getByCondition", method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取")
    public Result<Page<MessageSend>> getByCondition(@ModelAttribute MessageSend ms,
                                                    @ModelAttribute PageVo pv){

        Page<MessageSend> page = messageSendService.findByCondition(ms, PageUtil.initPage(pv));
        // lambda
        page.getContent().forEach(item->{
            User u = userService.get(item.getUserId());
            item.setUsername(u.getUsername());
            Message m = messageService.get(item.getMessageId());
            item.setTitle(m.getTitle());
            item.setContent(m.getContent());
            item.setType(m.getType());
        });
        return new ResultUtil<Page<MessageSend>>().setData(page);
    }
}
