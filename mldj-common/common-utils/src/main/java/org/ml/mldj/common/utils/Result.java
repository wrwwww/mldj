package org.ml.mldj.common.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.ml.mldj.common.exception.BizException;

import java.io.Serializable;
import java.util.function.Consumer;

@Getter
@Setter
@AllArgsConstructor
public class Result<T> implements Serializable {
    private int code;
    private String msg;
    private T data;

    public static <T> Result<T> success() {
        return new Result<>(200, "操作成功", null);
    }

    public static <T> Result<T> success(T data, String msg) {
        return new Result<>(200, msg, data);
    }

    public static <T> Result<T> success(T data) {
        return success(data, "操作成功");
    }

    public static <T> Result<T> error(int code, String msg) {
        return new Result<>(code, msg, null);
    }

    public static <T> Result<T> error(ResultCode resultCode) {
        return error(resultCode.getCode(), resultCode.getMsg());
    }


    public T unwrap() {
        if (code != 200) {
            throw new BizException(ResultCode.DATA_NO_EXISTS);
        }
        return data;
    }
    public boolean isOk(){
        return code == 200;
    } public boolean isErr(){
        return code != 200;
    }
}
