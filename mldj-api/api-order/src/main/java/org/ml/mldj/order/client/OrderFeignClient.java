package org.ml.mldj.order.client;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.dto.PageForm;
import org.ml.mldj.model.dto.customer.OrderForm;
import org.ml.mldj.model.vo.PageVO;
import org.ml.mldj.model.vo.customer.OrderVO;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("service-order")
public interface OrderFeignClient {

    Result<OrderVO> createOrder(OrderForm orderForm);

    Result<OrderVO> query(String orderId, String customerId);

    Result<Integer> del(String orderId, String customerId);

    Result<PageVO<OrderVO>> page(PageForm form, String customerId);
}
