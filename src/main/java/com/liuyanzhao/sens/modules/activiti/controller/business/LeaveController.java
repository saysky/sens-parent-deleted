package com.liuyanzhao.sens.modules.activiti.controller.business;

import com.liuyanzhao.sens.base.SensBaseController;
import com.liuyanzhao.sens.common.utils.ResultUtil;
import com.liuyanzhao.sens.common.utils.SecurityUtil;
import com.liuyanzhao.sens.common.vo.Result;
import com.liuyanzhao.sens.modules.activiti.entity.ActBusiness;
import com.liuyanzhao.sens.modules.activiti.entity.business.Leave;
import com.liuyanzhao.sens.modules.activiti.service.ActBusinessService;
import com.liuyanzhao.sens.modules.activiti.service.business.LeaveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * @author 言曌
 */
@Slf4j
@RestController
@Api(description = "请假申请接口")
@Transactional
@RequestMapping(value = "/sens/leave")
public class LeaveController extends SensBaseController<Leave, String> {

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private ActBusinessService actBusinessService;

    @Autowired
    private SecurityUtil securityUtil;

    @Override
    public LeaveService getService() {
        return leaveService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "添加申请草稿状态")
    public Result<Object> add(@ModelAttribute Leave leave,
                              @RequestParam String procDefId){

        Leave le = leaveService.save(leave);
        // 保存至我的申请业务
        String userId = securityUtil.getCurrUser().getId();
        ActBusiness actBusiness = new ActBusiness();
        actBusiness.setUserId(userId);
        actBusiness.setTableId(le.getId());
        actBusiness.setProcDefId(procDefId);
        actBusiness.setTitle(leave.getTitle());
        actBusinessService.save(actBusiness);
        return new ResultUtil<Object>().setData(null);
    }
}
