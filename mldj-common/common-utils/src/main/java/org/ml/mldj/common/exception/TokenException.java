package org.ml.mldj.common.exception;

import lombok.Getter;
import lombok.Setter;
import org.ml.mldj.common.utils.ResultCode;

public class TokenException extends RuntimeException {
    @Getter
    @Setter
    private int code;

    public TokenException(String message) {
        super(message);
    }

    public TokenException(String message, int code) {
        this(message);
        this.code = code;
    }
    public TokenException(String message, int code, Throwable cause) {
        super(message, cause);
        this.code = code;
    }


    public TokenException(ResultCode resultCode) {
        this(resultCode.getMsg(), resultCode.getCode());
    }
}
