package org.ml.mldj.customer.client;


import org.ml.mldj.model.vo.CustomerVO;
import org.mldj.common.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("service-customer")
public interface CustomerFeignClient {
   Result<CustomerVO> getCustomer(Integer id);
}
