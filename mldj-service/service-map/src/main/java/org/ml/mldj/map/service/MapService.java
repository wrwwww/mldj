package org.ml.mldj.map.service;

import org.ml.mldj.map.config.GaoDeConfig;
import org.ml.mldj.model.dto.OrderMileageAndMinuteForm;
import org.ml.mldj.model.vo.OrderMileageAndMinuteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MapService {
//    @Autowired
//    RestTemplate restTemplate;
    @Autowired
    GaoDeConfig gaoDeConfig;

    public OrderMileageAndMinuteVO calculateOrderMileageAndMinute(OrderMileageAndMinuteForm orderMileageAndMinuteForm) {
        return null;
    }
}
