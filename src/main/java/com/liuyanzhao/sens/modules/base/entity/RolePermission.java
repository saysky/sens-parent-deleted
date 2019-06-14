package com.liuyanzhao.sens.modules.base.entity;

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
@Table(name = "t_role_permission")
@TableName("t_role_permission")
@ApiModel(value = "角色权限")
public class RolePermission extends SensBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色id")
    private String roleId;

    @ApiModelProperty(value = "权限id")
    private String permissionId;
}