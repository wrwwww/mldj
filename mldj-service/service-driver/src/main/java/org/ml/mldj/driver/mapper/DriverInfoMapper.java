package org.ml.mldj.driver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.ml.mldj.model.driver.dto.DriverPageForm;
import org.ml.mldj.model.driver.entity.DriverInfo;
import org.ml.mldj.model.driver.vo.DriverVO;

/**
 * <p>
 * 司机信息表 Mapper 接口
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
public interface DriverInfoMapper extends BaseMapper<DriverInfo> {

    Page<DriverVO> queryDriverPage(Page<DriverVO> page, DriverPageForm form);
}
