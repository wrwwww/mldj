package org.ml.mldj.common.exception;

import lombok.Getter;
import lombok.Setter;
import org.ml.mldj.common.utils.ResultCode;

public class BizException extends RuntimeException {
    @Getter
    @Setter
    private int code;

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, int code) {
        this(message);
        this.code = code;
    }

    public BizException(ResultCode resultCode) {
        this(resultCode.getMsg(), resultCode.getCode());
    }

}
