package org.ml.mldj.driver.controller;

import com.alibaba.fastjson.JSON;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ml.mldj.driver.client.DriverFeignClient;
import org.ml.mldj.driver.client.DriverLocationFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Component
@ServerEndpoint("/ws/driver/{driverId}")
@Slf4j
public class DriverWebSocket {
    @Autowired
    DriverLocationFeignClient driverLocationFeignClient;

    @Autowired
    DriverFeignClient driverFeignClient;

    // 保存所有连接的司机
    private static final ConcurrentHashMap<Long, Session> driverSessions =
            new ConcurrentHashMap<>();

    // Redis键前缀
    private static final String DRIVER_SESSION_KEY = "driver:session:";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 连接建立
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("driverId") Long driverId) {
        driverSessions.put(driverId, session);

        // 记录到Redis，支持分布式部署
        String key = DRIVER_SESSION_KEY + driverId;
        redisTemplate.opsForValue().set(key, session.getId(), 1, TimeUnit.HOURS);

        log.info("司机连接WebSocket: driverId={}, 当前在线司机数={}",
                driverId, driverSessions.size());

        // 发送连接成功消息
        sendMessage(driverId, new WebSocketMessage(
                "CONNECT_SUCCESS",
                "连接成功",
                LocalDateTime.now()
        ));
    }

    /**
     * 收到消息
     */
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("driverId") Long driverId) {
        log.info("收到司机消息: driverId={}, message={}", driverId, message);

        try {
            WebSocketMessage wsMessage = JSON.parseObject(message, WebSocketMessage.class);

            switch (wsMessage.getType()) {
                case "HEARTBEAT":
                    // 心跳，更新连接时间
                    handleHeartbeat(driverId);
                    break;

                case "LOCATION_UPDATE":
                    // 位置更新
                    handleLocationUpdate(driverId, wsMessage.getData());
                    break;

                case "ORDER_GRAB":
                    // 司机抢单
                    handleOrderGrab(driverId, wsMessage.getData());
                    break;

                case "ORDER_CANCEL":
                    // 司机取消订单
                    handleOrderCancel(driverId, wsMessage.getData());
                    break;
            }
        } catch (Exception e) {
            log.error("处理WebSocket消息失败", e);
        }
    }

    /**
     * 连接关闭
     */
    @OnClose
    public void onClose(Session session, @PathParam("driverId") String driverId) {
        driverSessions.remove(driverId);

        driverFeignClient.Offline(driverId);


        log.info("司机断开WebSocket: driverId={}", driverId);
    }

//    /**
//     * 发送订单通知给司机
//     */
//    public boolean sendOrderNotification(Long driverId, OrderNotificationMessage notification) {
//        try {
//            Session session = driverSessions.get(driverId);
//            if (session != null && session.isOpen()) {
//                WebSocketMessage wsMessage = new WebSocketMessage(
//                        "ORDER_NOTIFICATION",
//                        "新订单通知",
//                        notification
//                );
//
//                session.getBasicRemote().sendText(JSON.toJSONString(wsMessage));
//                log.info("WebSocket订单通知发送成功: driverId={}, orderId={}",
//                        driverId, notification.getOrderId());
//                return true;
//            } else {
//                log.warn("司机不在线或连接已关闭: driverId={}", driverId);
//                return false;
//            }
//        } catch (Exception e) {
//            log.error("WebSocket发送消息失败: driverId={}", driverId, e);
//            return false;
//        }
//    }
//
//    /**
//     * 发送抢单结果给司机
//     */
//    public void sendGrabResult(Long driverId, boolean success, String message, Order order) {
//        Map<String, Object> data = new HashMap<>();
//        data.put("success", success);
//        data.put("message", message);
//        data.put("order", order);
//
//        WebSocketMessage wsMessage = new WebSocketMessage(
//                "GRAB_RESULT",
//                data,
//
//                );
//
//        sendMessage(driverId, wsMessage);
//    }

    /**
     * 广播消息给多个司机
     */
    public void broadcastToDrivers(List<Long> driverIds, WebSocketMessage message) {
        for (Long driverId : driverIds) {
            sendMessage(driverId, message);
        }
    }

    /**
     * 发送消息
     */
    private void sendMessage(Long driverId, WebSocketMessage message) {
        try {
            Session session = driverSessions.get(driverId);
            if (session != null && session.isOpen()) {
                session.getBasicRemote().sendText(JSON.toJSONString(message));
            }
        } catch (Exception e) {
            log.error("发送WebSocket消息失败: driverId={}", driverId, e);
        }
    }

    /**
     * 处理心跳
     */
    private void handleHeartbeat(Long driverId) {
        // 更新心跳时间
        String key = "driver:heartbeat:" + driverId;
        redisTemplate.opsForValue().set(key, String.valueOf(System.currentTimeMillis()),
                70, TimeUnit.SECONDS); // 70秒过期

        // 回复心跳
        sendMessage(driverId, new WebSocketMessage.WebSocketMessageBuilder().type("HEARTBEAT_RESPONSE").data(null).timestamp(LocalDateTime.now()).build());

    }

    /**
     * 处理位置更新
     */
    private void handleLocationUpdate(String driverId, Object data) {
        try {
            Map<String, Double> locationData = (Map<String, Double>) data;
            Double longitude = locationData.get("longitude");
            Double latitude = locationData.get("latitude");
            driverLocationFeignClient.update(driverId, longitude, latitude);
            log.debug("司机位置已更新: driverId={}, lng={}, lat={}",
                    driverId, longitude, latitude);
        } catch (Exception e) {
            log.error("处理位置更新失败", e);
        }
    }
}

// WebSocket消息封装
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
class WebSocketMessage {
    private String type;        // 消息类型
    //    private String content;     // 消息内容
    private Object data;        // 消息数据
    private LocalDateTime timestamp = LocalDateTime.now();
}