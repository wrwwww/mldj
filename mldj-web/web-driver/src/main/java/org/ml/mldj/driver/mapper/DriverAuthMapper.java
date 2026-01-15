package org.ml.mldj.driver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.ml.mldj.model.driver.DriverAuthStatus;
import org.ml.mldj.model.driver.entity.DriverAuth;

@Mapper
public interface DriverAuthMapper extends BaseMapper<DriverAuth> {
    int updateDriverAuthStatus(String id, DriverAuthStatus newStatus, DriverAuthStatus oldStatus);
}
