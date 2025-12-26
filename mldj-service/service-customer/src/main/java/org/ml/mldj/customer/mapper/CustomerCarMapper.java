package org.ml.mldj.customer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.ml.mldj.model.customer.entity.CustomerCar;

import java.util.List;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author mailang
 * @since 2025-12-10
 */
@Mapper
public interface CustomerCarMapper extends BaseMapper<CustomerCar> {
    int delByIdAndCustomerId(@Param("carId") String carId, @Param("customerId") String customerId);

    List<CustomerCar> queryByCustomerId(@Param("customer") String customerId);

    int add(@Param("form") CustomerCar form);
}
