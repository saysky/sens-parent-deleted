package com.liuyanzhao.sens.modules.base.entity;

import com.liuyanzhao.sens.base.SensBaseEntity;
import com.liuyanzhao.sens.common.constant.CommonConstant;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author 言曌
 */
@Data
@Entity
@Table(name = "t_department_header")
@TableName("t_department_header")
@ApiModel(value = "部门负责人")
public class DepartmentHeader extends SensBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "关联部门id")
    private String departmentId;

    @ApiModelProperty(value = "关联部门负责人")
    private String userId;

    @ApiModelProperty(value = "负责人类型 默认0主要 1副职")
    private Integer type = CommonConstant.HEADER_TYPE_MAIN;
}