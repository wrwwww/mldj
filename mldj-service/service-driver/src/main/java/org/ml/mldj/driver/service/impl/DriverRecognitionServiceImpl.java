package org.ml.mldj.driver.service.impl;

import org.ml.mldj.model.entity.DriverRecognition;
import org.ml.mldj.model.mapper.DriverRecognitionMapper;
import org.ml.mldj.model.service.IDriverRecognitionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 司机人脸识别表 服务实现类
 * </p>
 *
 * @author mailang
 * @since 2025-12-10
 */
@Service
public class DriverRecognitionServiceImpl extends ServiceImpl<DriverRecognitionMapper, DriverRecognition> implements IDriverRecognitionService {

}
