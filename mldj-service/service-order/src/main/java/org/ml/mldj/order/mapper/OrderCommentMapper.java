package org.ml.mldj.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.ml.mldj.model.order.entity.OrderComment;

/**
 * <p>
 * 订单评价表 Mapper 接口
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Mapper
public interface OrderCommentMapper extends BaseMapper<OrderComment> {

}
