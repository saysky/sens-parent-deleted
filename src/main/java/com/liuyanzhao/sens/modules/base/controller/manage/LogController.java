package com.liuyanzhao.sens.modules.base.controller.manage;

import com.liuyanzhao.sens.common.utils.PageUtil;
import com.liuyanzhao.sens.common.utils.ResultUtil;
import com.liuyanzhao.sens.common.vo.PageVo;
import com.liuyanzhao.sens.common.vo.Result;
import com.liuyanzhao.sens.common.vo.SearchVo;
import com.liuyanzhao.sens.modules.base.entity.Log;
import com.liuyanzhao.sens.modules.base.entity.elasticsearch.EsLog;
import com.liuyanzhao.sens.modules.base.service.LogService;
import com.liuyanzhao.sens.modules.base.service.elasticsearch.EsLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


/**
 * @author 言曌
 */
@Slf4j
@RestController
@Api(description = "日志管理接口")
@RequestMapping("/sens/log")
@Transactional
public class LogController{

    @Value("${sens.logRecord.es}")
    private Boolean esRecord;

    @Autowired
    private EsLogService esLogService;

    @Autowired
    private LogService logService;

    @RequestMapping(value = "/getAllByPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取全部")
    public Result<Object> getAllByPage(@RequestParam(required = false) Integer type,
                                       @RequestParam String key,
                                       @ModelAttribute SearchVo searchVo,
                                       @ModelAttribute PageVo pageVo){

        if(esRecord){
            Page<EsLog> es = esLogService.findByConfition(type, key, searchVo, PageUtil.initPage(pageVo));
            return new ResultUtil<Object>().setData(es);
        }else{
            Page<Log> log = logService.findByConfition(type, key, searchVo, PageUtil.initPage(pageVo));
            return new ResultUtil<Object>().setData(log);
        }
    }

    @RequestMapping(value = "/delByIds/{ids}", method = RequestMethod.DELETE)
    @ApiOperation(value = "批量删除")
    public Result<Object> delByIds(@PathVariable String[] ids){

        for(String id : ids){
            if(esRecord){
                esLogService.deleteLog(id);
            }else{
                logService.delete(id);
            }
        }
        return new ResultUtil<Object>().setSuccessMsg("删除成功");
    }

    @RequestMapping(value = "/delAll", method = RequestMethod.DELETE)
    @ApiOperation(value = "全部删除")
    public Result<Object> delAll(){

        if(esRecord){
            esLogService.deleteAll();
        }else{
            logService.deleteAll();
        }
        return new ResultUtil<Object>().setSuccessMsg("删除成功");
    }
}
