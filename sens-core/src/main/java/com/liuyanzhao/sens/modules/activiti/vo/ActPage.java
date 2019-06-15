package com.liuyanzhao.sens.modules.activiti.vo;

import lombok.Data;

import java.util.List;

/**
 * @author 言曌
 */
@Data
public class ActPage<T> {

    List<T> content;

    Long totalElements;
}
