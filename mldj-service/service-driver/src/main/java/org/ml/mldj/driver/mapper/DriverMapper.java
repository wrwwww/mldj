package org.ml.mldj.driver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.ml.mldj.model.dto.DriverPageForm;
import org.ml.mldj.model.entity.Driver;
import org.ml.mldj.model.vo.DriverVO;

/**
 * <p>
 * 代驾司机表 Mapper 接口
 * </p>
 *
 * @author mailang
 * @since 2025-12-10
 */
public interface DriverMapper extends BaseMapper<Driver> {
    Page<DriverVO> queryDriverPage(Page<DriverVO> page, DriverPageForm form);
}
