package org.mldj.common.utils;

import lombok.Getter;

@Getter
public enum ResultCode {
    SYS_ERROR(1001, "系统异常，请联系管理员!"),
    PARAM_LOST(2001, "参数错误!"),
    DUPLICATE_DATA(2002, "改名称已存在"),
    DATA_NO_EXISTS(2003, "数据不存在"),
    EXISTS_SON(2004, "存在子项目");

    private final int code;
    private final String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}