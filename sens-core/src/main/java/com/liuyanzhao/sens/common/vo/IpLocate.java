package com.liuyanzhao.sens.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 言曌
 */
@Data
public class IpLocate implements Serializable {

    private String retCode;

    private City result;
}

