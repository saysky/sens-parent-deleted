package com.liuyanzhao.sens.modules.base.entity;

import com.liuyanzhao.sens.base.SensBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author 言曌
 */
@Data
@Entity
@Table(name = "t_dict")
@TableName("t_dict")
@ApiModel(value = "字典")
public class Dict extends SensBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字典名称")
    private String title;

    @ApiModelProperty(value = "字典类型")
    private String type;

    @ApiModelProperty(value = "备注")
    private String description;

    @ApiModelProperty(value = "排序值")
    @Column(precision = 10, scale = 2)
    private BigDecimal sortOrder;
}