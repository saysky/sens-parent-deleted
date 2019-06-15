package com.liuyanzhao.sens.modules.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 言曌
 * @date 2019-06-13 22:01
 */
@Data
@Entity
@Table(name = "t_option")
@TableName("t_option")
@ApiModel(value = "设置")
@NoArgsConstructor
@AllArgsConstructor
public class Option {

    @Id
    @TableId(type = IdType.INPUT)
    @Column(unique = true, nullable = false)
    private String optionName;

    private String optionValue;

}
