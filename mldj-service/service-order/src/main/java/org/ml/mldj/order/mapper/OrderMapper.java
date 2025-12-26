package org.ml.mldj.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.ml.mldj.model.order.entity.Order;
import org.ml.mldj.model.order.dto.OrderPageForm;
import org.ml.mldj.model.order.vo.OrderVO;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

   int updateOrderDriver(@Param("orderId") String orderId,@Param("driverId") String driverId);

    Order queryOrderById(@Param("orderId") String orderId);

    Page<OrderVO> page(OrderPageForm pageForm);
}
