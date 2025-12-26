package org.ml.mldj.driver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ml.mldj.model.driver.entity.DriverFaceRecognition;
import org.ml.mldj.driver.mapper.DriverFaceRecognitionMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 司机人脸识别记录表 服务实现类
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Service
public class DriverFaceRecognitionServiceImpl extends ServiceImpl<DriverFaceRecognitionMapper, DriverFaceRecognition> implements IService<DriverFaceRecognition> {

}
