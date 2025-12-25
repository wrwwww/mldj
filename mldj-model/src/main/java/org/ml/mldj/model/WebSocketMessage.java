package org.ml.mldj.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// WebSocket消息封装
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class WebSocketMessage {
    private String type;        // 消息类型
    //    private String content;     // 消息内容
    private Object data;        // 消息数据
    private LocalDateTime timestamp = LocalDateTime.now();
}