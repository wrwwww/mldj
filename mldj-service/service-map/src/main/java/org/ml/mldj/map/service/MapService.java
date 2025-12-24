package org.ml.mldj.map.service;

import com.alibaba.fastjson2.JSONObject;
import org.ml.mldj.common.exception.BizException;
import org.ml.mldj.common.utils.HttpUtils;
import org.ml.mldj.map.client.GaodeFeignClient;
import org.ml.mldj.map.config.GaoDeConfig;
import org.ml.mldj.model.dto.OrderMileageAndMinuteForm;
import org.ml.mldj.model.dto.map.DistanceForm;
import org.ml.mldj.model.vo.OrderMileageAndMinuteVO;
import org.ml.mldj.model.vo.map.DistanceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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

}
