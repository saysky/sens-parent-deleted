package com.liuyanzhao.sens.modules.activiti.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 言曌
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Assignee {

    private String username;

    private Boolean isExecutor;
}
