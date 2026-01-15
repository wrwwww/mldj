package org.ml.mldj.model.driver;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DriverWorkStatus {
    ONLINE(0, "在线"),
    OFFLINE(1, "离线");
    @EnumValue
    @JsonValue
    private final int code;
    private final String desc;

}
