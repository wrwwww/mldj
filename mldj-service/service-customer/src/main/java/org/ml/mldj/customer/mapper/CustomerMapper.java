package org.ml.mldj.customer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.ml.mldj.model.entity.Customer;

/**
 * <p>
 *  用户 Mapper 接口
 * </p>
 *
 * @author mailang
 * @since 2025-12-10
 */
@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {

}
