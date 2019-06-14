package com.liuyanzhao.sens.modules.base.controller.common;

import com.liuyanzhao.sens.common.utils.IpInfoUtil;
import com.liuyanzhao.sens.common.utils.ResultUtil;
import com.liuyanzhao.sens.common.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 言曌
 */
@Slf4j
@RestController
@Api(description = "IP接口")
@RequestMapping("/sens/common/ip")
@Transactional
public class IpInfoController {

    @Autowired
    private IpInfoUtil ipInfoUtil;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ApiOperation(value = "IP及天气相关信息")
    public Result<Object> upload(HttpServletRequest request) {

        String result= ipInfoUtil.getIpWeatherInfo(ipInfoUtil.getIpAddr(request));
        return new ResultUtil<Object>().setData(result);
    }
}