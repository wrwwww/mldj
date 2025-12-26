package org.ml.mldj.model.common.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WebSocketMessageType {
    ORDER_GRAB("ORDER_GRAB"),
    ORDER_CANCEL("ORDER_CANCEL"),
    LOCATION_UPDATE("LOCATION_UPDATE"),
    HEARTBEAT("HEARTBEAT");

    final String messageType;


}
