package com.liuyanzhao.sens.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 言曌
 */
@Data
public class City implements Serializable {

    String country;

    String province;

    String city;
}
