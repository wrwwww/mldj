package org.ml.mldj.common.utils;

import lombok.Getter;

@Getter
public enum ResultCode {
    SYS_ERROR(1001, "系统异常，请联系管理员!"),
    PARAM_LOST(2001, "参数错误!"),
    DUPLICATE_DATA(2002, "改名称已存在"),
    DATA_NO_EXISTS(2003, "数据不存在"),
    EXISTS_SON(2004, "存在子项目"),

    ORDER_EXIST(3000, "订单已经被抢走了"),

    TOKEN_EXPIRED(1, "JWT令牌已过期"),
    TOKEN_INVALID(2, "不支持的令牌格式"),
    TOKEN_MALFORMED(3, "令牌格式错误"),
    TOKEN_SIGNATURE(4, "令牌签名无效"),
    TOKEN_ILLEGALARGUMENT(5, "令牌参数错误"),

    ;


    private final int code;
    private final String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}