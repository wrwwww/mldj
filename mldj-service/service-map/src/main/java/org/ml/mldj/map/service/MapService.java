package org.ml.mldj.map.service;

import com.alibaba.fastjson2.JSONObject;
import org.ml.mldj.common.exception.BizException;
import org.ml.mldj.common.utils.HttpUtils;
import org.ml.mldj.common.utils.MapUtils;
import org.ml.mldj.map.client.GaodeFeignClient;
import org.ml.mldj.map.config.GaoDeConfig;
import org.ml.mldj.model.map.dto.*;
import org.ml.mldj.model.order.dto.OrderMileageAndMinuteForm;
import org.ml.mldj.model.order.vo.OrderMileageAndMinuteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;


@Service
public class MapService {

    @Autowired
    GaoDeConfig gaoDeConfig;
    @Autowired
    GaodeFeignClient gaodeFeignClient;

    public OrderMileageAndMinuteVO calculateOrderMileageAndMinute(OrderMileageAndMinuteForm orderMileageAndMinuteForm) {
        DistanceForm drivingDirectionForm = new DistanceForm();
        drivingDirectionForm.setKey(gaoDeConfig.getKey());
        drivingDirectionForm.setOrigin(orderMileageAndMinuteForm.getStartPlaceLatitude() + ',' + orderMileageAndMinuteForm.getStartPlaceLongitude());
        drivingDirectionForm.setDestination(orderMileageAndMinuteForm.getEndPlaceLatitude() + ',' + orderMileageAndMinuteForm.getEndPlaceLongitude());
        String url = HttpUtils.buildUrl("https://restapi.amap.com", "/v3/distance", drivingDirectionForm);
        String result = gaodeFeignClient.geo(url);
        JSONObject jsonObject = JSONObject.parseObject(result);
        if (jsonObject.get("status").equals("1")) {
            throw new BizException("");
        }
        OrderMileageAndMinuteVO mileageAndMinuteVO = new OrderMileageAndMinuteVO();
        DistanceVO result1 = (DistanceVO) jsonObject.get("result");

        mileageAndMinuteVO.setDistance(result1.getDistance());
        mileageAndMinuteVO.setDuration(result1.getDuration());
        return mileageAndMinuteVO;
    }

    public Object estimateRoute(List<EstimateRouteForm.Route> routes) {
        // 排序
        routes.sort(Comparator.comparingInt(EstimateRouteForm.Route::getId));
        //
        String origin = MapUtils.PointToString(routes.get(0).getLatitude(), routes.get(0).getLongitude());
        int size = routes.size() - 1;
        String destination = MapUtils.PointToString(routes.get(size).getLatitude(), routes.get(size).getLongitude());
//        经度和纬度用","分割，经度在前，纬度在后，小数点后不超过6位，坐标点之间用";"分隔
//        最大数目：16个坐标点。如果输入多个途径点，则按照用户输入的顺序进行路径规划
        StringBuilder sb = new StringBuilder();
        routes.subList(1, size - 1).forEach(route -> {
            sb.append(MapUtils.PointToString(route.getLatitude(), route.getLongitude()));
            sb.append(";");
        });
        sb.delete(sb.length() - 2, sb.length() - 1);

        // 构建请求参数
        EstimateRoute estimateRoute = new EstimateRoute();
        estimateRoute.setDestination(destination);
        estimateRoute.setOrigin(origin);
        estimateRoute.setWaypoints(sb.toString());

        // 构建请求
        String url = HttpUtils.buildUrl("https://restapi.amap.com", "/v3/distance/driving", estimateRoute);
        String result = gaodeFeignClient.geo(url);
        JSONObject jsonObject = JSONObject.parseObject(result);
        if (jsonObject.get("status").equals("1")) {
            throw new BizException("");
        }
        return jsonObject.get("result");
    }

    public Object search(SearchPlaceForm form) {
        form.setKey(gaoDeConfig.getKey());
        String url = HttpUtils.buildUrl("https://restapi.amap.com", "/v3/place/text", form);
        String result = gaodeFeignClient.geo(url);
        JSONObject jsonObject = JSONObject.parseObject(result);
        if (jsonObject.get("status").equals("1")) {
            throw new BizException("");
        }
         return jsonObject.get("pois");
    }
}
