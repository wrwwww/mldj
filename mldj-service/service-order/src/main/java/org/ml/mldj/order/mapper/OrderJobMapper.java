package org.ml.mldj.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.ml.mldj.model.order.entity.OrderJob;

/**
 * <p>
 * 订单任务关联表 Mapper 接口
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */

@Mapper
public interface OrderJobMapper extends BaseMapper<OrderJob> {

}
