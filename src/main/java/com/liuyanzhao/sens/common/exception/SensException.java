package com.liuyanzhao.sens.common.exception;

import lombok.Data;

/**
 * @author 言曌
 */
@Data
public class SensException extends RuntimeException {

    private String msg;

    public SensException(String msg){
        super(msg);
        this.msg = msg;
    }
}
