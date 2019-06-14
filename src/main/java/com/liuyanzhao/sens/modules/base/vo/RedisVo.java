package com.liuyanzhao.sens.modules.base.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 言曌
 */
@Data
@AllArgsConstructor
public class RedisVo {

    private String key;

    private String value;
}
