package org.ml.mldj.driver.service;

import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.driver.dto.DriverLoginForm;
import org.ml.mldj.model.driver.dto.DriverPageForm;
import org.ml.mldj.model.entity.Driver;
import com.baomidou.mybatisplus.extension.service.IService;
import org.ml.mldj.model.driver.vo.DriverSettingVO;
import org.ml.mldj.model.driver.vo.DriverVO;
import org.ml.mldj.model.common.PageVO;

/**
 * <p>
 * 代驾司机表 服务类
 * </p>
 *
 * @author mailang
 * @since 2025-12-10
 */
public interface IDriverService extends IService<Driver> {

    Driver getDriverByOpenId(String openid);

    String registerNewDriver(DriverLoginForm form);

    DriverVO query(String driverId);

    DriverSettingVO queryDriverSetting(String driverId);

    PageVO<DriverVO> queryDriverPage(DriverPageForm form);

    void offline(String driverId);

    Result<?> snatchingOrder(String driverId, String orderId);
}
