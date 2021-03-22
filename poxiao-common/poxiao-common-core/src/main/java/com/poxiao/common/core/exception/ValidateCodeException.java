package com.poxiao.common.core.exception;

/**
 * @author qinqi
 * @date 2020/9/11
 */
public class ValidateCodeException extends RuntimeException{

    public ValidateCodeException() {}

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
