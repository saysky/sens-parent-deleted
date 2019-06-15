package com.liuyanzhao.sens.modules.activiti.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 言曌
 */
@Data
public class EmailMessage implements Serializable {

    private String username;

    private String content;

    private String fullUrl;
}
