package com.liuyanzhao.sens.modules.activiti.entity;

import com.liuyanzhao.sens.base.SensBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author 言曌
 */
@Data
@Entity
@Table(name = "t_act_model")
@TableName("t_act_model")
@ApiModel(value = "模型")
public class ActModel extends SensBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "模型名称")
    private String name;

    @ApiModelProperty(value = "标识")
    private String modelKey;

    @ApiModelProperty(value = "版本")
    private Integer version;

    @ApiModelProperty(value = "描述/备注")
    private String description;
}