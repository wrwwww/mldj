package org.ml.mldj.driver.service.impl;

import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.dto.map.DriverFilter;
import org.ml.mldj.model.dto.map.NearbyDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.domain.geo.Metrics;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.ml.mldj.common.constant.RedisConst.DRIVER_GEO_KEY;
import static org.ml.mldj.common.constant.RedisConst.DRIVER_INFO_KEY_PREFIX;

@Service
public class DriverLocationService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    private final GeoOperations<String, String> geoOps;

    public DriverLocationService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.geoOps = redisTemplate.opsForGeo();
    }

    /**
     * 更新司机位置
     */
    public void updateDriverLocation(String driverId, double longitude, double latitude) {
        // 1. 存储地理位置
        Point point = new Point(longitude, latitude);
        geoOps.add(DRIVER_GEO_KEY, point, driverId);

        // 2. 更新司机信息
        Map<String, String> driverInfo = new HashMap<>();
        driverInfo.put("latitude", String.valueOf(latitude));
        driverInfo.put("longitude", String.valueOf(longitude));
        driverInfo.put("updateTime", LocalDateTime.now().toString());
        redisTemplate.opsForHash().putAll(DRIVER_INFO_KEY_PREFIX + driverId, driverInfo);

        // 3. 设置过期时间（防止僵尸数据）
        redisTemplate.expire(DRIVER_INFO_KEY_PREFIX + driverId, 10, TimeUnit.MINUTES);
    }

    /**
     * 查找附近司机
     *
     * @param centerLng 中心点经度
     * @param centerLat 中心点纬度
     * @param radius    搜索半径（米）
     * @param limit     返回数量限制
     * @return 附近的司机列表
     */
    public Result<List<NearbyDriver>> findNearbyDrivers(
            double centerLng, double centerLat,
            double radius, int limit) {

        // 搜索指定半径内的司机
        Circle within = new Circle(
                new Point(centerLng, centerLat),
                new Distance(radius, Metrics.METERS)
        );

        GeoResults<RedisGeoCommands.GeoLocation<String>> results = geoOps.radius(
                DRIVER_GEO_KEY,
                within,
                RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs()
                        .includeDistance()
                        .includeCoordinates()
                        .sortAscending()      // 按距离升序
                        .limit(limit)
        );

        if (results != null) {
            return Result.success(results.getContent().stream()
                    .map(result -> {
                        RedisGeoCommands.GeoLocation<String> location = result.getContent();
                        Distance distance = result.getDistance();
                        Point point = location.getPoint();

                        return NearbyDriver.builder()
                                .driverId(location.getName())
                                .distance(distance.getValue())
                                .latitude(point.getY())
                                .longitude(point.getX())
                                .build();
                    })
                    .collect(Collectors.toList()));
        }
        return Result.success();
    }

    /**
     * 查找附近司机（带过滤条件）
     */
    public Result<List<NearbyDriver>> findNearbyDriversWithFilter(
            double centerLng, double centerLat,
            double radius, int limit,
            DriverFilter filter) {

        // 1. 先获取附近的所有司机
        // 先获取多一些
        return findNearbyDrivers(
                centerLng, centerLat, radius, 100);
//        // 2. 并行处理，获取详细信息并过滤
//        return nearbyDrivers.parallelStream()
//                .map(nearby -> getDriverDetail(nearby.getDriverId()))
//                .filter(Objects::nonNull)
//                .filter(driver -> filterDriver(driver, filter))
//                .sorted(Comparator.comparingDouble(driver ->
//                        calculateDistance(centerLat, centerLng,
//                                driver.getLatitude(), driver.getLongitude())))
//                .limit(limit)
//                .collect(Collectors.toList());
    }
}
