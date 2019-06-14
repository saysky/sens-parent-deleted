package com.liuyanzhao.sens.modules.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 言曌
 */
@Data
public class OtherSetting implements Serializable{

    @ApiModelProperty(value = "域名")
    private String domain;

    @ApiModelProperty(value = "IP黑名单")
    private String blacklist;
}
