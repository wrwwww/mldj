package org.ml.mldj.common.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class Result<T> implements Serializable {
    private int code;
    private String msg;
    private T data;


    public static <T> Result<T> success(T data, String msg) {
        return new Result<>(200, msg, data);
    }

    public static <T> Result<T> success(T data) {
        return success(data, "操作成功");
    }

    public static <T> Result<T> error(int code, String msg) {
        return new Result<>(code, msg, null);
    }

    public Boolean isOk() {
        return code == 200;
    }

    public Boolean isErr() {
        return code != 200;
    }

    public T unwrap() {
        return data;
    }
}
