package org.ml.mldj.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.ml.mldj.model.order.dto.OrderPageForm;
import org.ml.mldj.model.order.entity.OrderInfo;
import org.ml.mldj.model.order.vo.OrderVO;

/**
 * <p>
 * 订单信息表 Mapper 接口
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Mapper
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {

    OrderInfo queryOrderById(@Param("orderId") String orderId);

    Page<OrderVO> page(OrderPageForm pageForm);
}
