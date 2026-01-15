package org.ml.mldj.model.driver;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum DriverAuthStatus {
    INIT(1,"初始化"),
    ID_UPLOADED(2,"身份证上传"),
    LICENSE_UPLOADED(3,"驾驶证上传"),
    FACE_UPLOADED(4,"人脸上传"),
    VERIFYING(5,""),
    VERIFIED(6,""),
    REJECTED(7,""),
    FROZEN(8,"");

    @EnumValue
    private final Integer code;
    @JsonValue  // JSON序列化时返回的值（可选）
    private final String desc;
    // Jackson反序列化：根据code创建枚举
    @JsonCreator
    public static DriverAuthStatus fromCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (DriverAuthStatus status : DriverAuthStatus.values()) {
            if (status.code.equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("无效的状态码: " + code);
    }

    // 也可以根据描述反序列化
    public static DriverAuthStatus fromDesc(String desc) {
        if (desc == null) {
            return null;
        }
        for (DriverAuthStatus status : DriverAuthStatus.values()) {
            if (status.desc.equals(desc)) {
                return status;
            }
        }
        return null;
    }

}
